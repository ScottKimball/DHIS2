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
            addProgramsV2();
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


    private void addDataElements() {
        List<DataElementDto> partialDtos = dhisWebService.getResources("dataElements", DataElementDto.class);

        for (DataElementDto partialDto : partialDtos) {
            DataElementDto fullDataElementDto = dhisWebService.getResource(partialDto.getHref(), DataElementDto.class);
            dataElementService.createFromDetails(fullDataElementDto);
        }
    }


    private void addAttributes()  {
        List<TrackedEntityAttributeDto> partialDtos = dhisWebService.getResources("trackedEntityAttributes", TrackedEntityAttributeDto.class);
        for (TrackedEntityAttributeDto partialDto : partialDtos) {
            TrackedEntityAttributeDto fullTrackedEntityAttributeDto = dhisWebService.getResource(partialDto.getHref(), TrackedEntityAttributeDto.class);
            trackedEntityAttributeService.createFromDetails(fullTrackedEntityAttributeDto);
        }
    }

    private void addTrackedEntities() {
        List<TrackedEntityDto> partialDtos = dhisWebService.getResources("trackedEntities", TrackedEntityDto.class);
        for (TrackedEntityDto partialDto : partialDtos) {
            TrackedEntityDto fullTrackedEntityDto = dhisWebService.getResource(partialDto.getHref(), TrackedEntityDto.class);
            trackedEntityService.createFromDetails(fullTrackedEntityDto);
        }
    }

    private void addProgramsV2() {
        List<ProgramDto> partialDtos = dhisWebService.getResources("programs", ProgramDto.class);

        for (ProgramDto partialDto : partialDtos) {
            ProgramDto fullDto = dhisWebService.getResource(partialDto.getHref(), ProgramDto.class);
            Program program = programService.createFromDetails(fullDto);

            if (fullDto.getTrackedEntityDto() != null) {
                program.setTrackedEntity(getProgramTrackedEntityFromDto(fullDto.getTrackedEntityDto()));
            }

            if (fullDto.getProgramStageDtos() != null) {
                program.setStages(getStagesFromDtos(fullDto.getProgramStageDtos(), program.getUuid(), program.hasRegistration()));
            }

            if (fullDto.getProgramTrackedEntityAttributeDtos() != null) {
                program.setAttributes(getTrackedEntityAttributesFromDetails(fullDto.getProgramTrackedEntityAttributeDtos()));
            }

            programService.update(program);
        }
    }

    private TrackedEntity getProgramTrackedEntityFromDto(TrackedEntityDto partialDto) {
        TrackedEntity trackedEntity = trackedEntityService.findById(partialDto.getId());
        if (trackedEntity == null) {
            TrackedEntityDto fullDto = dhisWebService.getResource(partialDto.getHref(), TrackedEntityDto.class);
            trackedEntity = trackedEntityService.createFromDetails(fullDto);
        }
        return trackedEntity;
    }

    private List<Stage> getStagesFromDtos(List<ProgramStageDto> dtos, String programId, boolean hasRegistration) {
        List<Stage> stages = new ArrayList<Stage>();

        for (ProgramStageDto partialDto : dtos) {
            Stage stage = stageService.findById(partialDto.getId());

            if (stage == null) {
                ProgramStageDto fullDto = dhisWebService.getResource(partialDto.getHref(), ProgramStageDto.class);
                stage = stageService.createFromDetails(fullDto, programId, hasRegistration);
                stage.setDataElements(getStageDataElementsFromDetails(fullDto.getProgramStageDataElementDtos()));
            }
            stages.add(stage);
        }

        return stages;
    }

    private List<DataElement> getStageDataElementsFromDetails(List<ProgramStageDataElementDto> dtos) {
        List<DataElement> dataElements = new ArrayList<DataElement>();

        for (ProgramStageDataElementDto dto : dtos) {
            DataElementDto dataElementDto = dto.getDataElementDto();
            DataElement dataElement = dataElementService.findById(dataElementDto.getId());
            if (dataElement == null) {
                dataElement = dataElementService.createFromDetails(dataElementDto);
            }
            dataElements.add(dataElement);
        }
        return dataElements;
    }

    private List<TrackedEntityAttribute> getTrackedEntityAttributesFromDetails(List<ProgramTrackedEntityAttributeDto> dtos) {
        List<TrackedEntityAttribute> trackedEntityAttributes = new ArrayList<TrackedEntityAttribute>();

        for (ProgramTrackedEntityAttributeDto dto : dtos) {
            TrackedEntityAttributeDto trackedEntityAttributeDto = dto.getTrackedEntityAttributeDto();
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

    private void addOrgUnits() {
        List<OrganisationUnitDto> partialDtos = dhisWebService.getResources("organisationUnits", OrganisationUnitDto.class);
        for (OrganisationUnitDto partialDto : partialDtos) {
            OrganisationUnitDto fullOrganisationUnitDto = dhisWebService.getResource(partialDto.getHref(), OrganisationUnitDto.class);
            orgUnitService.createFromDetails(fullOrganisationUnitDto);
        }
    }
}
