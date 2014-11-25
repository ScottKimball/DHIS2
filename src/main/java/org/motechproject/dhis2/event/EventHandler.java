package org.motechproject.dhis2.event;

import com.jayway.jsonpath.JsonPath;
import org.motechproject.dhis2.domain.*;
import org.motechproject.dhis2.dto.*;
import org.motechproject.dhis2.http.HttpConstants;
import org.motechproject.dhis2.http.HttpQuery;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.repository.*;
import org.motechproject.dhis2.service.EnrollmentService;
import org.motechproject.dhis2.service.RegistrationService;
import org.motechproject.dhis2.service.StageService;
import org.motechproject.event.listener.EventRelay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.springframework.stereotype.Component;


import java.util.*;


/**
 * Created by scott on 8/26/14.
 */
@Component
public class EventHandler {

    /*HardWired UUID values (Not needed currently but keeping them for reference right now)
    private String trackedEntityUUID = "cyl5vuJ5ETQ"; // uuid for person tracked entity
    private String programUUID = "ur1Edk5Oe2n";
    private String orgUnitUUID = "g8upMTyEZGZ";
    private String lastNameUUID = "hwlRTFIFSUq";
    private String firstNameUUID = "dv3nChNSIxy";
    private String genderUUID = "cejWyOfXge6";
    private String nationalIdentifierUUID = "AuPLng5hLbE";
    private String stage_uuid = "vxQUcroMY0r";
    */

    private Logger logger = LoggerFactory.getLogger(EventHandler.class);
    private EventRelay eventRelay;
    private EnrollmentService enrollmentService;
    private ProgramDataService programDataService;
    private StageService stageService;
    private TrackedEntityDataService trackedEntityDataService;
    private OrgUnitDataService orgUnitDataService;
    private RegistrationService registrationService;
    private AttributeDataService attributeDataService;
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;
    private StageDataService stageDataService;
    private HttpQuery httpQuery;


    @Autowired
    public EventHandler( EventRelay eventRelay,
                         EnrollmentService enrollmentService, ProgramDataService programDataService,
                         StageService stageService,
                         TrackedEntityDataService trackedEntityDataService,
                         OrgUnitDataService orgUnitDataService,
                         RegistrationService registrationService,
                         AttributeDataService attributeDataService,
                         TrackedEntityInstanceDataService trackedEntityInstanceDataService,
                         StageDataService stageDataService,
                         HttpQuery httpQuery
                        ){
        this.eventRelay = eventRelay;
        this.enrollmentService = enrollmentService;
        this.programDataService = programDataService;
        this.stageService = stageService;
        this.trackedEntityDataService = trackedEntityDataService;
        this.orgUnitDataService = orgUnitDataService;
        this.registrationService = registrationService;
        this.attributeDataService = attributeDataService;
        this.trackedEntityInstanceDataService = trackedEntityInstanceDataService;
        this.stageDataService = stageDataService;
        this.httpQuery = httpQuery;


    }


    /*This will be the generic entity registration event handler*/
    @MotechListener(subjects = {EventSubjects.REGISTER_ENTITY})
    public void handleRegistration(MotechEvent event) {

    }

    /*This will be the generic program enrollment event handler */
    @MotechListener(subjects = {EventSubjects.ENROLL_IN_PROGRAM})
    public void handleEnrollment(MotechEvent event) {

    }

    /*This will be the generic program stage event handler */
    @MotechListener(subjects = {EventSubjects.UPDATE_PROGRAM_STAGE})
    public void handleStageUpdate(MotechEvent event) {

    }

    @MotechListener(subjects = {EventSubjects.TB_REGISTER_ENTITY})
    public void handleTbEntityRegistration(MotechEvent event){

        Map<String , Object> params = event.getParameters();
        List<Attribute> attributeList = new ArrayList<Attribute>();
        String externalUUID =(String) params.get(EventParams.EXTERNAL_ID);

        /*Get tracked Entity UUID */
        String entityType = (String) params.get(EventParams.ENTITY_TYPE);
        TrackedEntityMapper trackedEntityMapper = trackedEntityDataService.findByExternalName(entityType);
        String trackedEntity = trackedEntityMapper.getDhis2Uuid();

        /*Get program UUID*/
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

        registrationService.send(instance);
    }


    @MotechListener(subjects  = {EventSubjects.TB_ENROLL})
    public void handleTbEnrollment(MotechEvent event) {

        Map<String,Object> params =  event.getParameters();
        Map<String,String> additionalParams =(Map) params.get(EventParams.ADDITIONAL_ATTRIBUTES);
        List<Attribute> programAttributes = new ArrayList<Attribute>();

        String program = (String) params.get(EventParams.PROGRAM);
        ProgramMapper programMapper = programDataService.findByDhis2Name(program);

        /*Get all program attributes from DHIS2. At some point, we should have a boolean flag for
        * whether or not attributes are mapped locally.*/
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

        String date = (String) params.get(EventParams.DATE_REGISTERED);
        date = date != null ? date : "";
        Enrollment enrollment = new Enrollment(programMapper.getDhis2Uuid(),instanceMapper.getDhis2Uuid(),
                date,programAttributes);

        enrollmentService.send(enrollment);
    }

    @MotechListener(subjects = {EventSubjects.TB_FOLLOW_UP})
    public void handleTbFollowUp(MotechEvent event) {

        logger.debug("In Handle Tb follow up");
        Map<String,Object> params = event.getParameters();

        /*Get instance uuid*/
        TrackedEntityInstanceMapper instanceMapper = trackedEntityInstanceDataService.findByExternalName((String)params.get("externalId"));

        /*TODO: call to dhis2 for orgUnit info*/
        /*Right now just hardwire it in*/
        OrgUnitMapper orgUnitMapper = orgUnitDataService.findByExternalName("Njandama_MCHP");

        String program = (String)params.get("program");
        ProgramMapper programMapper = programDataService.findByDhis2Name(program);

        String followUpDate = (String) params.get("date");

        StageMapper stageMapper = stageDataService.findByExternalName(event.getSubject());

        Stage stage = new Stage(programMapper.getDhis2Uuid(),orgUnitMapper.getDhis2Uuid(),followUpDate,
                stageMapper.getDhis2Uuid(),instanceMapper.getDhis2Uuid());

        stageService.send(stage);
        }



}
