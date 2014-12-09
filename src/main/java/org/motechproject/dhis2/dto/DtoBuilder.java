package org.motechproject.dhis2.dto;

import com.jayway.jsonpath.JsonPath;
import org.motechproject.dhis2.domain.*;
import org.motechproject.dhis2.dto.impl.Enrollment;
import org.motechproject.dhis2.dto.impl.Stage;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstance;
import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.http.HttpConstants;
import org.motechproject.dhis2.http.HttpQuery;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.repository.*;
import org.motechproject.event.MotechEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**TODO: break apart and refactor methods.
 * Created by scott on 12/2/14.
 */
@Component
@Scope(value = "singleton")
public class DtoBuilder {


    private ProgramDataService programDataService;
    private TrackedEntityDataService trackedEntityDataService;
    private OrgUnitDataService orgUnitDataService;
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;
    private StageDataService stageDataService;
    private HttpQuery httpQuery;

    private Logger logger = LoggerFactory.getLogger(DtoBuilder.class);

    @Autowired
    public DtoBuilder(ProgramDataService programDataService, TrackedEntityDataService trackedEntityDataService,
                      OrgUnitDataService orgUnitDataService,
                      TrackedEntityInstanceDataService trackedEntityInstanceDataService,
                      StageDataService stageDataService, HttpQuery httpQuery) {
        this.programDataService = programDataService;
        this.trackedEntityDataService = trackedEntityDataService;
        this.orgUnitDataService = orgUnitDataService;
        this.trackedEntityInstanceDataService = trackedEntityInstanceDataService;
        this.stageDataService = stageDataService;
        this.httpQuery = httpQuery;
    }



    public Dto createDto (MotechEvent event,DtoType dtoType) {

        switch (dtoType) {
            case ENROLLMENT:
                return createEnrollment(event);

            case REGISTRATION:
                return createInstance(event);

            case STAGE_UPDATE:
                return createStage(event);

        }

        return null;

    }

    private Enrollment createEnrollment (MotechEvent event) {

        Map<String,Object> params =  event.getParameters();

        /*Get program UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String program = (String) params.get(EventParams.PROGRAM);
        ProgramMapper programMapper = programDataService.findByDhis2Name(program);

        Request programRequest = new Request(HttpConstants.PROGRAM_PATH + "/" +
                programMapper.getDhis2Uuid(),"");
        Object jsonResponse = httpQuery.send(programRequest);

        List<Attribute> attributeList = new ArrayList<>();

        List<Object> trackedEntityAttributes = JsonPath.read
                (jsonResponse, "$..programTrackedEntityAttributes[*].attribute");

        /* Iterates down program attributes and adds them to attribute list. */
        for (Object o : trackedEntityAttributes) {
            String attributeName = JsonPath.read(o,"$.name");
            String attributeId = JsonPath.read(o,"$.id");
            String attributeValue = (String) params.get(attributeName);

          /*  Might need to check if required attribute is present here */
            if(attributeValue != null) {
                attributeList.add(new Attribute(attributeName,attributeId,attributeValue));

            }
        }

         /*Get trackedEntityInstance UUID from MDS*/
        String externalId = (String) params.get(EventParams.EXTERNAL_ID);
        TrackedEntityInstanceMapper instanceMapper = trackedEntityInstanceDataService.findByExternalName(externalId);

        String date = (String) params.get(EventParams.DATE);
        date = date != null ? date : "";

        Enrollment enrollment = new Enrollment(programMapper.getDhis2Uuid(),instanceMapper.getDhis2Uuid(),
                date,attributeList);

        return enrollment;
    }

    private Stage createStage (MotechEvent event) {

        Map<String,Object> params = event.getParameters();

        /*Get instance uuid*/
        TrackedEntityInstanceMapper instanceMapper = trackedEntityInstanceDataService.
                findByExternalName((String) params.get(EventParams.EXTERNAL_ID));

        /*Get Org Unit UUID*/
        String orgUnit = (String) params.get(EventParams.LOCATION);
        String orgUnitUuid = getOrgUnit(orgUnit);

        /*Get program UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String program = (String)params.get(EventParams.PROGRAM);
        ProgramMapper programMapper = programDataService.findByDhis2Name(program);
        String programUuid = programMapper.getDhis2Uuid();

        String followUpDate = (String) params.get(EventParams.DATE);

        /*Get stage UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String stageName = (String) params.get(EventParams.STAGE);
        StageMapper stageMapper = stageDataService.findByExternalName(stageName);
        String stageUuid = null;
        List<Attribute> attributeList = new ArrayList<>();

        /*No local mapping to stage. Must check DHIS2 for identical name*/
        if (stageMapper == null) {
            Request programRequest = new Request(HttpConstants.PROGRAM_PATH + "/" +
                    programMapper.getDhis2Uuid(),"");

            Object jsonResponse = httpQuery.send(programRequest);
            List<Object> stages = JsonPath.read
                    (jsonResponse, "$..programStages.[?(@.name==" + stageName + ")]");

            /*No stages with that name. Must terminate*/
            if (stages.size() == 0) {
                logger.error("No stage found with name \"" + stageName + "\"");
                /*TODO: exception*/
                return null;


            } else {


                /*TODO: Find a solution that doesn't use Object as the type */
                List<Object> programTrackedEntityAttributes = JsonPath.read
                        (jsonResponse, "$..programTrackedEntityAttributes[*].attribute");

                 /* Iterates down program attributes and adds them to attribute list. */
                for (Object o : programTrackedEntityAttributes) {
                    String attributeName = JsonPath.read(o, "$.name");
                    String attributeId = JsonPath.read(o, "$.id");
                    String attributeValue = (String) params.get(attributeName);

                     /*  Might need to check if required attribute is present here */
                    if (attributeValue != null) {
                        attributeList.add(new Attribute(attributeName, attributeId, attributeValue));

                    }
                }
            }

        } else {
            stageUuid = stageMapper.getDhis2Uuid();
        }

        Stage stage = new Stage(programUuid,orgUnitUuid,followUpDate,
                stageUuid,instanceMapper.getDhis2Uuid(),attributeList);
        return stage;
    }

    private TrackedEntityInstance createInstance (MotechEvent event) {

        Map<String , Object> params = event.getParameters();
        List<Attribute> attributeList = new ArrayList<Attribute>();
        String externalUUID =(String) params.get(EventParams.EXTERNAL_ID);

        /*Get tracked Entity UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID */
        String entityType = (String) params.get(EventParams.ENTITY_TYPE);
        TrackedEntityMapper trackedEntityMapper = trackedEntityDataService.findByExternalName(entityType);
        String trackedEntity = trackedEntityMapper.getDhis2Uuid();

        /*Get program UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String program = (String) params.get(EventParams.PROGRAM);
        ProgramMapper programMapper = programDataService.findByDhis2Name(program);

        /*Get org Unit*/
        String orgUnit = (String) params.get(EventParams.LOCATION);
        String orgUnitUuid = getOrgUnit(orgUnit);

        /*Get program information from DHIS2 server*/
        Request attributesRequest = new Request( HttpConstants.TRACKED_ENTITY_ATTRIBUTES_PATH +
                HttpConstants.NO_PAGING_NO_LINKS,"");
        Object jsonResponse = httpQuery.send(attributesRequest);

        /*TODO: Find a solution that doesn't use Object as the type */
        List<Object> trackedEntityAttributes = JsonPath.read
                (jsonResponse, "$..trackedEntityAttributes[*]");

        /* Iterates down program attributes and adds them to attribute list. */
        for (Object o : trackedEntityAttributes) {
            String attributeName = JsonPath.read(o,"$.name");
            String attributeId = JsonPath.read(o,"$.id");
            String attributeValue = (String) params.get(attributeName);

          /*  Might need to check if required attribute is present here */
            if(attributeValue != null) {
                attributeList.add(new Attribute(attributeName,attributeId,attributeValue));

            }
        }

        TrackedEntityInstance instance = new TrackedEntityInstance(externalUUID,
                trackedEntity,attributeList,orgUnitUuid);

        return instance;
    }

    /*First checks for local mapping. If there is no local mapping, calls dhis2 and checks to see if orgUnit exists
     * Returns UUID for orgunit if it exists.  */
    private String getOrgUnit (String orgUnit) {

        OrgUnitMapper orgUnitMapper = orgUnitDataService.findByExternalName(orgUnit);

        /*No local mapping*/
        if (orgUnitMapper == null) {

            Request orgUnitRequest = new Request(HttpConstants.ORG_UNITS_PATH +
                    HttpConstants.QUERY + orgUnit + HttpConstants.NO_PAGING_NO_LINKS, "");

            Object jsonResponse =  httpQuery.send(orgUnitRequest);

            List<Object> orgUnits = JsonPath.read
                    (jsonResponse, "$..organisationUnits[*]");

            /*This should not happen. There should be one and only one match*/
            if (orgUnits.size() != 1) {
                /*TODO*/
                return null;
            }


            return JsonPath.read(orgUnits.get(0),"$.id");

        }

        return orgUnitMapper.getDhis2Uuid();
    }

}
