package org.motechproject.dhis2.dto.builder;

import com.jayway.jsonpath.JsonPath;
import org.motechproject.dhis2.domain.OrgUnitMapper;
import org.motechproject.dhis2.domain.ProgramMapper;
import org.motechproject.dhis2.domain.StageMapper;
import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.dto.Dto;
import org.motechproject.dhis2.dto.DtoType;
import org.motechproject.dhis2.dto.impl.AttributeDto;
import org.motechproject.dhis2.dto.impl.DataElementDto;
import org.motechproject.dhis2.dto.impl.EnrollmentDto;
import org.motechproject.dhis2.dto.impl.StageDto;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.http.HttpConstants;
import org.motechproject.dhis2.http.HttpQuery;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.OrgUnitMapperDataService;
import org.motechproject.dhis2.repository.ProgramMapperDataService;
import org.motechproject.dhis2.repository.StageMapperDataService;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.event.MotechEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by scott on 12/2/14.
 */
@Component
@Scope(value = "singleton")
public class DtoBuilder {


    private ProgramMapperDataService programMapperDataService;
    private OrgUnitMapperDataService orgUnitMapperDataService;
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;
    private StageMapperDataService stageMapperDataService;
    private HttpQuery httpQuery;
    private OrgUnitDataService orgUnitDataService;

    private Logger logger = LoggerFactory.getLogger(DtoBuilder.class);


    @Autowired
    public DtoBuilder(ProgramMapperDataService programMapperDataService,
                      OrgUnitMapperDataService orgUnitMapperDataService,
                      TrackedEntityInstanceDataService trackedEntityInstanceDataService,
                      StageMapperDataService stageMapperDataService, HttpQuery httpQuery,
                      OrgUnitDataService orgUnitDataService) {
        this.programMapperDataService = programMapperDataService;
        this.orgUnitMapperDataService = orgUnitMapperDataService;
        this.trackedEntityInstanceDataService = trackedEntityInstanceDataService;
        this.stageMapperDataService = stageMapperDataService;
        this.httpQuery = httpQuery;
        this.orgUnitDataService = orgUnitDataService;
    }


    public Dto createDto(MotechEvent event, DtoType dtoType) {

        switch (dtoType) {
            case ENROLLMENT:
                return createEnrollment(event);

            case REGISTRATION:
                return createInstance(event);

            case STAGE_UPDATE:
                return createStage(event);

            default:
                break;

        }

        return null;

    }

    private EnrollmentDto createEnrollment(MotechEvent event) {

        Map<String, Object> params = event.getParameters();

        /*Get program UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String program = (String) params.get(EventParams.PROGRAM);
        ProgramMapper programMapper = programMapperDataService.findByDhis2Name(program);

        Request programRequest = new Request(HttpConstants.PROGRAM_PATH + "/" +
                programMapper.getDhis2Uuid());
        Object jsonResponse = httpQuery.send(programRequest);

        List<AttributeDto> attributeDtoList = new ArrayList<>();

        List<Object> trackedEntityAttributes = JsonPath.read(jsonResponse, "$..programTrackedEntityAttributes[*].attribute");

        /* Iterates down program attributes and adds them to attribute list. */
        for (Object o : trackedEntityAttributes) {
            String attributeName = JsonPath.read(o, "$.name");
            String attributeId = JsonPath.read(o, "$.id");
            String attributeValue = (String) params.get(attributeName);

          /*  Might need to check if required attribute is present here */
            if (attributeValue != null) {
                attributeDtoList.add(new AttributeDto(attributeName, attributeId, attributeValue));

            }
        }

         /*Get trackedEntityInstance UUID from MDS*/
        String externalId = (String) params.get(EventParams.EXTERNAL_ID);
        TrackedEntityInstanceMapper instanceMapper = trackedEntityInstanceDataService.findByExternalName(externalId);

        String date = (String) params.get(EventParams.DATE);
        date = date != null ? date : "";

        EnrollmentDto enrollmentDto = new EnrollmentDto(programMapper.getDhis2Uuid(), instanceMapper.getDhis2Uuid(),
                date, attributeDtoList);

        return enrollmentDto;
    }

    private StageDto createStage(MotechEvent event) {

        Map<String, Object> params = event.getParameters();

        /*Get instance uuid*/
        TrackedEntityInstanceMapper instanceMapper = trackedEntityInstanceDataService.
                findByExternalName((String) params.get(EventParams.EXTERNAL_ID));

        /*Get Org Unit UUID*/
        String orgUnit = (String) params.get(EventParams.LOCATION);
        String orgUnitUuid = getOrgUnit(orgUnit);

        /*Get program UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String program = (String) params.get(EventParams.PROGRAM);
        ProgramMapper programMapper = programMapperDataService.findByDhis2Name(program);
        String programUuid = programMapper.getDhis2Uuid();

        String followUpDate = (String) params.get(EventParams.DATE);

        /*Get stage UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String stageName = (String) params.get(EventParams.STAGE);
        StageMapper stageMapper = stageMapperDataService.findByExternalName(stageName);
        String stageUuid = stageMapper.getDhis2Uuid();


        Request stageRequest = new Request(HttpConstants.STAGES_PATH + "/" + stageUuid);
        Object stageResponse = httpQuery.send(stageRequest);

        List<Object> dataElements = JsonPath.read(stageResponse, "$..programStageDataElements[*]");
        List<DataElementDto> stageDataElementDtos = new ArrayList<>();

        for (Object o : dataElements) {
            String name = JsonPath.read(o, "$.dataElement.name");
            String id = JsonPath.read(o, "$.dataElement.id");
            String value = (String) params.get(name);

            if (value != null) {
                stageDataElementDtos.add(new DataElementDto(name, id, value));
            }

        }

        StageDto stageDto = new StageDto(programUuid, orgUnitUuid, followUpDate,
                stageUuid, instanceMapper.getDhis2Uuid(), stageDataElementDtos);
        return stageDto;
    }

    private TrackedEntityInstanceDto createInstance(MotechEvent event) {

        Map<String, Object> params = event.getParameters();
        List<AttributeDto> attributeDtoList = new ArrayList<AttributeDto>();

        String externalUUID = (String) params.remove(EventParams.EXTERNAL_ID);
        String trackedEntity = (String) params.remove(EventParams.ENTITY_TYPE);

        /*Get org Unit*/
        String orgUnit = (String) params.remove(EventParams.LOCATION);
        String orgUnitUuid = null;
        try {
            orgUnitUuid = orgUnitDataService.findByName(orgUnit).getUuid();

        } catch (NullPointerException e) {
            logger.error("Org Unit Name not found");
            logger.error(e.toString());
            return null;
        }

        /*Iterate over remaining parameters*/
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();

            if (entry.getValue() != null) {
                attributeDtoList.add(new AttributeDto(null, (String) entry.getKey(), (String) entry.getValue()));
            }

        }

        return new TrackedEntityInstanceDto(externalUUID, trackedEntity, attributeDtoList, orgUnitUuid);
    }

    /*First checks for local mapping. If there is no local mapping, calls dhis2 and checks to see if orgUnit exists
     * Returns UUID for orgunit if it exists.  */
    private String getOrgUnit(String orgUnit) {

        OrgUnitMapper orgUnitMapper = orgUnitMapperDataService.findByExternalName(orgUnit);

        /*No local mapping*/
        if (orgUnitMapper == null) {

            Request orgUnitRequest = new Request(HttpConstants.ORG_UNITS_PATH +
                    HttpConstants.QUERY + orgUnit + "&" + HttpConstants.NO_PAGING_NO_LINKS);

            Object jsonResponse = httpQuery.send(orgUnitRequest);

            List<Object> orgUnits = JsonPath.read(jsonResponse, "$..organisationUnits[*]");

            /*This should not happen. There should be one and only one match*/
            if (orgUnits.size() != 1) {
                /*TODO*/
                return null;
            }

            return JsonPath.read(orgUnits.get(0), "$.id");
        }
        return orgUnitMapper.getDhis2Uuid();
    }

}
