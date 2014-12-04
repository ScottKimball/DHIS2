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
import java.util.Iterator;
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
    private AttributeDataService attributeDataService;
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;
    private StageDataService stageDataService;
    private HttpQuery httpQuery;

    private Logger logger = LoggerFactory.getLogger(DtoBuilder.class);

    @Autowired
    public DtoBuilder(ProgramDataService programDataService, TrackedEntityDataService trackedEntityDataService,
                      OrgUnitDataService orgUnitDataService, AttributeDataService attributeDataService,
                      TrackedEntityInstanceDataService trackedEntityInstanceDataService,
                      StageDataService stageDataService, HttpQuery httpQuery) {
        this.programDataService = programDataService;
        this.trackedEntityDataService = trackedEntityDataService;
        this.orgUnitDataService = orgUnitDataService;
        this.attributeDataService = attributeDataService;
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
        Map<String,String> additionalParams =(Map) params.get(EventParams.ADDITIONAL_ATTRIBUTES);
        List<Attribute> programAttributes = new ArrayList<Attribute>();



        /*Get program UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String program = (String) params.get(EventParams.PROGRAM);
        ProgramMapper programMapper = programDataService.findByDhis2Name(program);

        Request programRequest = new Request(HttpConstants.BASE_URL + HttpConstants.PROGRAM_PATH +
                programMapper.getDhis2Uuid(),"");
        Object jsonResponse = httpQuery.send(programRequest);


        Iterator<Map.Entry<String,String>> itr = additionalParams.entrySet().iterator();

        /*Iterates over additional attributes. There are three possibilities for each attribute in the map:
        * 1. The external name is different than the DHIS2 name. If this is the case, the user must provide a mapping
        * using AttributeMapper in the MDS browser.
        * 2. The external name is the same as the DHIS2 name. If this is the case, We will grab the attribute
        * information from the DHIS2 server. The user must make sure the names are unique for valid mapping.
        * 3. The external name provided does not correspond to an attribute in DHIS2. If so, it is ignored.
        * */
        while(itr.hasNext()) {

            Map.Entry<String,String> entrySet = itr.next();
            String key = entrySet.getKey();
            AttributeMapper mapper = attributeDataService.findByExternalName(key);

            /*External name is different than DHIS2 name and user has entered mapping into MDS databrowser*/
            if (mapper != null) {
                programAttributes.add(new Attribute(mapper.getExternalName(),mapper.getDhis2Uuid(),
                        entrySet.getValue()));

            /*External name is identical to DHIS2 name. */
            } else {

                /*This could return more than one entry. If so, that indicates a user error. User must use unique
                * names in order to have a valid mapping.*/
                List<Object> attributeNameList = JsonPath.read(jsonResponse,"$..programTrackedEntityAttributes[*]." +
                        "attribute.[?(@.name ==" + key +  ")]");

                if (attributeNameList.size() == 0 ) {
                    logger.warn("No attribute found for attribute name \"" + key + "\"" + " .Attribute not included " +
                            "in the submission to the DHIS2 server");

                } else if (attributeNameList.size() > 1) {
                    logger.warn("Multiple attributes found for attribute name   \"" + key + "\"" + ". Names must be " +
                            "unique. Attribute not included in submission to DHIS2 server");

                } else {
                    Object desiredAttribute = attributeNameList.get(0);
                    String attributeName = JsonPath.read(desiredAttribute,"$.name");
                    String attributeId = JsonPath.read(desiredAttribute,"$.id");
                    String attributeValue = entrySet.getValue();

                    if(attributeValue != null) {
                        programAttributes.add(new Attribute(attributeName,attributeId,attributeValue));

                     /*TODO: Check if the attribute is mandatory. If so, throw exception, print error, and terminate. */
                    } else if (false) {}

                }

            }
        }

         /*Get trackedEntityInstance UUID from MDS*/
        String externalId = (String) params.get(EventParams.EXTERNAL_ID);
        TrackedEntityInstanceMapper instanceMapper = trackedEntityInstanceDataService.findByExternalName(externalId);

        String date = (String) params.get(EventParams.DATE);
        date = date != null ? date : "";
        Enrollment enrollment = new Enrollment(programMapper.getDhis2Uuid(),instanceMapper.getDhis2Uuid(),
                date,programAttributes);
        return enrollment;
    }

    private Stage createStage (MotechEvent event) {

        Map<String,Object> params = event.getParameters();

        /*Get instance uuid*/
        TrackedEntityInstanceMapper instanceMapper = trackedEntityInstanceDataService.
                findByExternalName((String) params.get(EventParams.EXTERNAL_ID));

        /*Get orgunit UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        OrgUnitMapper orgUnitMapper = orgUnitDataService.findByExternalName((String) params.get(EventParams.LOCATION));

        /*Get program UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String program = (String)params.get(EventParams.PROGRAM);
        ProgramMapper programMapper = programDataService.findByDhis2Name(program);

        String followUpDate = (String) params.get(EventParams.DATE);

        /*Get stage UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String stageName = (String) params.get(EventParams.STAGE);
        StageMapper stageMapper = stageDataService.findByExternalName(stageName);
        String stageUuid = null;
        List<Attribute> attributeList = new ArrayList<>();

        /*No local mapping to stage. Must check DHIS2 for identical name*/
        if (stageMapper == null) {
            Request programRequest = new Request(HttpConstants.BASE_URL + HttpConstants.PROGRAM_PATH +
                    programMapper.getDhis2Uuid(),"");

            Object jsonResponse = httpQuery.send(programRequest);
            List<Object> stages = JsonPath.read
                    (jsonResponse, "$..programStages.[?(@.name==" + stageName + ")]");

            /*No stages with that name. Must terminate*/
            if (stages.size() == 0) {
                logger.error("No stage found with name \"" + stageName + "\"");
                /*TODO: exception*/
                return null;


            /*Multiple stages with that name*/
            } else if (stages.size() > 1) {
                logger.error("Multiple stages found with name \"" + stageName + "\"" + "Stage names must be unique" +
                        "for a given program");
                /*TODO: exception*/
                return null;

                /*One matching stage*/
            } else {
                /*TODO: call to dhis2 to get stage data elements. Terminate if params don't contain compulsory elements*/

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


            /*TODO: Check if the attribute is mandatory. If so, throw exception, print error, and terminate.
             * TODO: user could also have provided mapping. check to see if there is a mapping from DHIS2 name
              * TODO: to  external name */
                    } else if (false) {
                    }


                }
            }


        } else {
            stageUuid = stageMapper.getDhis2Uuid();
        }

        Stage stage = new Stage(programMapper.getDhis2Uuid(),orgUnitMapper.getDhis2Uuid(),followUpDate,
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

        /*Get program information from DHIS2 server*/
        Request programRequest = new Request(HttpConstants.BASE_URL + HttpConstants.PROGRAM_PATH +
                programMapper.getDhis2Uuid(),"");
        Object jsonResponse = httpQuery.send(programRequest);

        /*TODO: Find a solution that doesn't use Object as the type */
        List<Object> programTrackedEntityAttributes = JsonPath.read
                (jsonResponse, "$..programTrackedEntityAttributes[*].attribute");

        /* Iterates down program attributes and adds them to attribute list. */
        for (Object o : programTrackedEntityAttributes) {
            String attributeName = JsonPath.read(o,"$.name");
            String attributeId = JsonPath.read(o,"$.id");
            String attributeValue = (String) params.get(attributeName);

          /*  Might need to check if required attribute is present here */
            if(attributeValue != null) {
                attributeList.add(new Attribute(attributeName,attributeId,attributeValue));


            /*TODO: Check if the attribute is mandatory. If so, throw exception, print error, and terminate.
             * TODO: user could also have provided mapping. check to see if there is a mapping from DHIS2 name
              * TODO: to  external name */
            } else if (false) {}

        }

        OrgUnitMapper orgUnitMapper = orgUnitDataService.findByExternalName((String) params.get(EventParams.LOCATION));

        String orgUnit = orgUnitMapper.getDhis2Uuid();
        TrackedEntityInstance instance = new TrackedEntityInstance(externalUUID,trackedEntity,attributeList,orgUnit);

        return instance;
    }

}
