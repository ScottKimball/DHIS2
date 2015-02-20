package org.motechproject.dhis2.service.impl;

import com.jayway.jsonpath.PathNotFoundException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.domain.Settings;
import org.motechproject.dhis2.domain.Stage;
import org.motechproject.dhis2.domain.TrackedEntity;
import org.motechproject.dhis2.domain.TrackedEntityAttribute;
import org.motechproject.dhis2.rest.domain.DataElementDto;
import org.motechproject.dhis2.rest.domain.OrganisationUnitDto;
import org.motechproject.dhis2.rest.domain.ProgramDto;
import org.motechproject.dhis2.rest.domain.ProgramStageDataElementDto;
import org.motechproject.dhis2.rest.domain.ProgramStageDto;
import org.motechproject.dhis2.rest.domain.ProgramTrackedEntityAttributeDto;
import org.motechproject.dhis2.rest.domain.TrackedEntityAttributeDto;
import org.motechproject.dhis2.rest.domain.TrackedEntityDto;
import org.motechproject.dhis2.service.DataElementService;
import org.motechproject.dhis2.rest.service.DhisWebService;
import org.motechproject.dhis2.service.OrgUnitService;
import org.motechproject.dhis2.service.ProgramService;
import org.motechproject.dhis2.service.SettingsService;
import org.motechproject.dhis2.service.StageService;
import org.motechproject.dhis2.service.SyncService;
import org.motechproject.dhis2.service.TrackedEntityAttributeService;
import org.motechproject.dhis2.service.TrackedEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SyncServiceImpl implements SyncService {
    @Autowired
    private DhisWebService dhisWebService;

    @Autowired
    private DataElementService dataElementService;

    @Autowired
    private TrackedEntityAttributeService trackedEntityAttributeService;

    @Autowired
    private TrackedEntityService trackedEntityService;

    @Autowired
    private ProgramService programService;

    @Autowired
    private StageService stageService;

    @Autowired
    private OrgUnitService orgUnitService;

    @Autowired
    @Qualifier("dhisSettingsService")
    private SettingsService settingsService;

    private Logger logger = LoggerFactory.getLogger(SyncServiceImpl.class);

    @Override
    public boolean sync() {
        logger.debug("Starting Sync");
        long startTime = System.nanoTime();
        Settings settings = settingsService.getSettings();

        if (!testConnection(settings)) {
            return false;
        }

        programService.deleteAll();
        trackedEntityAttributeService.deleteAll();
        trackedEntityService.deleteAll();
        orgUnitService.deleteAll();
        dataElementService.deleteAll();
        stageService.deleteAll();

        try {
            addDataElements();
            addAttributes();
            addTrackedEntities();
            addPrograms();
            addOrgUnits();

            long endTime = System.nanoTime();


            logger.debug("Sync Successful");
            logger.debug("Time for sync: " + TimeUnit.SECONDS.convert(endTime - startTime,
                    TimeUnit.NANOSECONDS) + "seconds");

            return true;
        } catch (PathNotFoundException e) {

            programService.deleteAll();
            trackedEntityAttributeService.deleteAll();
            trackedEntityService.deleteAll();
            orgUnitService.deleteAll();
            dataElementService.deleteAll();
            stageService.deleteAll();

            logger.error("Problem with DHIS2 application Schema.Sync Unsuccessful");
            logger.error(e.toString());
            return false;
        }
    }

    /**
     * Request data elements from DHIS and persist in MDS. The partial data element objects returned by the top-level api
     * endpoint suffice for current needs.
     */
    private void addDataElements() {
        List<DataElementDto> dataElementDtos = dhisWebService.getResources("dataElements", DataElementDto.class);

        for (DataElementDto dataElementDto : dataElementDtos) {
            dataElementService.createFromDetails(dataElementDto);
        }
    }

    /**
     * Request tracked entity attributes from DHIS and persist in MDS. The partial tracked entity attribute objects returned by
     * the top-level api endpoint suffice for current needs.
     */
    private void addAttributes()  {
        List<TrackedEntityAttributeDto> trackedEntityAttributeDtos = dhisWebService.getResources("trackedEntityAttributes", TrackedEntityAttributeDto.class);
        for (TrackedEntityAttributeDto trackedEntityAttributeDto : trackedEntityAttributeDtos) {
            trackedEntityAttributeService.createFromDetails(trackedEntityAttributeDto);
        }
    }

    /**
     * Request tracked entities from DHIS and persist in MDS. The partial tracked entity objects returned by the top-level
     * api endpoint suffice for current needs.
     */
    private void addTrackedEntities() {
        List<TrackedEntityDto> trackedEntityDtos = dhisWebService.getResources("trackedEntities", TrackedEntityDto.class);
        for (TrackedEntityDto trackedEntityDto : trackedEntityDtos) {
            trackedEntityService.createFromDetails(trackedEntityDto);
        }
    }

    /**
     * Request programs from DHIS and persist in MDS. Since we need data from full program objects, we load the partial
     * objects from the top-level endpoints and then iterate through the links for the complete objects.
     */
    private void addPrograms() {
        List<ProgramDto> partialDtos = dhisWebService.getResources("programs", ProgramDto.class);

        for (ProgramDto partialDto : partialDtos) {
            ProgramDto fullDto = dhisWebService.getResource(partialDto.getHref(), ProgramDto.class);
            Program program = programService.createFromDetails(fullDto);

            /**
             * Request and add the program's sub-objects (tracked entity, program stages, program tracked entity's attributes).
             */
            if (fullDto.getTrackedEntity() != null) {
                program.setTrackedEntity(getProgramTrackedEntityFromDto(fullDto.getTrackedEntity()));
            }

            if (fullDto.getProgramStages() != null) {
                program.setStages(getStagesFromDtos(fullDto.getProgramStages(), program.getUuid(), program.hasRegistration()));
            }

            if (fullDto.getProgramTrackedEntityAttributes() != null) {
                program.setAttributes(getTrackedEntityAttributesFromDtos(fullDto.getProgramTrackedEntityAttributes()));
            }

            programService.update(program);
        }
    }

    /**
     * Helper to load the program's tracked entity from MDS, if we've already persisted it, or make a request to DHIS and persist,
     * if we haven't.
     * @param partialDto
     *  The partial tracked entity dto from the program dto.
     * @return
     *  The MDS-managed tracked entity.
     */
    private TrackedEntity getProgramTrackedEntityFromDto(TrackedEntityDto partialDto) {
        TrackedEntity trackedEntity = trackedEntityService.findById(partialDto.getId());
        if (trackedEntity == null) {
            TrackedEntityDto fullDto = dhisWebService.getResource(partialDto.getHref(), TrackedEntityDto.class);
            trackedEntity = trackedEntityService.createFromDetails(fullDto);
        }
        return trackedEntity;
    }

    /**
     * Helper to load the program's program stages from MDS, if we've already persisted them, or make a request to DHIS, persist,
     * if we haven't.
     * @param partialStageDtos
     *  The program's partial stage dtos.
     * @param programId
     *  The id of the program.
     * @param hasRegistration
     *  Whether or not the stage's program is registered.
     * @return
     *  The MDS-managed program stages.
     */
    private List<Stage> getStagesFromDtos(List<ProgramStageDto> partialStageDtos, String programId, boolean hasRegistration) {
        List<Stage> stages = new ArrayList<Stage>();

        for (ProgramStageDto partialStageDto : partialStageDtos) {
            Stage stage = stageService.findById(partialStageDto.getId());

            if (stage == null) {
                ProgramStageDto fullDto = dhisWebService.getResource(partialStageDto.getHref(), ProgramStageDto.class);
                stage = stageService.createFromDetails(fullDto, programId, hasRegistration);
                stage.setDataElements(getStageDataElementsFromDtos(fullDto.getProgramStageDataElements()));
            }
            stages.add(stage);
        }

        return stages;
    }

    /**
     * Helper to load the program stage's data elements from MDS, if we've already persisted them, or make a request to DHIS,
     * persist, if we haven't.
     * @param programStageDataElementDtos
     *  The program stage's partial data element dtos.
     * @return
     *  The MDS-managed data elements.
     */
    private List<DataElement> getStageDataElementsFromDtos(List<ProgramStageDataElementDto> programStageDataElementDtos) {
        List<DataElement> dataElements = new ArrayList<DataElement>();

        for (ProgramStageDataElementDto programStageDataElementDto : programStageDataElementDtos) {
            DataElementDto dataElementDto = programStageDataElementDto.getDataElement();
            DataElement dataElement = dataElementService.findById(dataElementDto.getId());
            if (dataElement == null) {
                dataElement = dataElementService.createFromDetails(dataElementDto);
            }
            dataElements.add(dataElement);
        }
        return dataElements;
    }

    /**
     * Helper to laod the program's program tracked entity attributes from MDS, if we've already persisted them, or make a
     * request to DHIS and persist, if we haven't.
     * @param programTrackedEntityAttributeDtos
     * @return
     */
    private List<TrackedEntityAttribute> getTrackedEntityAttributesFromDtos(List<ProgramTrackedEntityAttributeDto> programTrackedEntityAttributeDtos) {
        List<TrackedEntityAttribute> trackedEntityAttributes = new ArrayList<TrackedEntityAttribute>();

        for (ProgramTrackedEntityAttributeDto programTrackedEntityAttributeDto : programTrackedEntityAttributeDtos) {
            TrackedEntityAttributeDto trackedEntityAttributeDto = programTrackedEntityAttributeDto.getTrackedEntityAttribute();
            TrackedEntityAttribute trackedEntityAttribute = trackedEntityAttributeService.findById(trackedEntityAttributeDto.getId());
            if (trackedEntityAttribute == null) {
                trackedEntityAttribute = trackedEntityAttributeService.createFromDetails(trackedEntityAttributeDto);
            }
            trackedEntityAttributes.add(trackedEntityAttribute);
        }
        return trackedEntityAttributes;
    }

    private boolean testConnection(Settings settings) {
        HttpGet get = new HttpGet(settings.getProgramURI());
        get.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(settings.getUsername(), settings.getPassword()),
                "UTF-8",
                false));

        try {
            HttpResponse httpResponse = new DefaultHttpClient().execute(get);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return true;
            }

        } catch (Exception e) {
            logger.error(e.toString());
            return false;
        }

        return false;
    }

    /**
     * Request organisation units from DHIS and persist in MDS. The partial organisation unit objects returned by the top-level
     * api endpoint suffice for current needs.
     */
    private void addOrgUnits() {
        List<OrganisationUnitDto> orgUnitDtos = dhisWebService.getResources("organisationUnits", OrganisationUnitDto.class);
        for (OrganisationUnitDto orgUnitDto : orgUnitDtos) {
            orgUnitService.createFromDetails(orgUnitDto);
        }
    }
}
