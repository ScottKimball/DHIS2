package org.motechproject.dhis2.event;

import com.google.gson.Gson;

import com.jayway.jsonpath.JsonPath;
import org.joda.time.DateTime;
import org.motechproject.dhis2.domain.*;
import org.motechproject.dhis2.dto.*;
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

    /*HardWired UUID values */
    private String trackedEntityUUID = "cyl5vuJ5ETQ"; // uuid for person tracked entity
    private String programUUID = "ur1Edk5Oe2n";
    private String orgUnitUUID = "g8upMTyEZGZ";
    private String lastNameUUID = "hwlRTFIFSUq";
    private String firstNameUUID = "dv3nChNSIxy";
    private String genderUUID = "cejWyOfXge6";
    private String nationalIdentifierUUID = "AuPLng5hLbE";
    private String stage_uuid = "vxQUcroMY0r";

    private static final String URL = "http://admin:district@localhost:8080/api";
    private static final String PROGRAM_PATH = "/programs/";


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
        String entityType = (String) params.get("entityType");
        TrackedEntityMapper trackedEntityMapper = trackedEntityDataService.findByExternalName(entityType);
        String trackedEntity = trackedEntityMapper.getDhis2Uuid();

        /*Get program UUID*/
        String program = (String) params.get("program");
        ProgramMapper programMapper = programDataService.findByDhis2Name(program);

        /*Get program information from DHIS2 server*/
        Request programRequest = new Request(URL + PROGRAM_PATH + programMapper.getDhis2Uuid(),"");
        Object jsonResponse = httpQuery.send(programRequest);
        List<Object> programTrackedEntityAttributes = JsonPath.read(jsonResponse, "$..programTrackedEntityAttributes[*].attribute");

        /*
        * Iterates down program attributes and adds them to attribute list.
        * */
        for (Object o : programTrackedEntityAttributes) {
            String attributeName = JsonPath.read(o,"$.name");
            String attributeId = JsonPath.read(o,"$.id");
            String attributeValue = (String) params.get(attributeName);

          /*  Might need to check if required attribute is present here */
            if(attributeValue != null) {
                attributeList.add(new Attribute(attributeName,attributeId,attributeValue));
            }
        }

        OrgUnitMapper orgUnitMapper = orgUnitDataService.findByExternalName((String) params.get(EventParams.LOCATION));

        String orgUnit = orgUnitMapper.getDhis2Uuid();
        TrackedEntityInstance instance = new TrackedEntityInstance(externalUUID,trackedEntity,attributeList,orgUnit);

        registrationService.send(instance);
    }


    @MotechListener(subjects  = {EventSubjects.TB_ENROLL})
    public void handleTbRegistration (MotechEvent event) {

        Map<String,Object> params =  event.getParameters();


        ProgramMapper programMapper = programDataService.findByExternalName((String)params.
                get(params.get(EventParams.PROGRAM)));

        /*Get trackedEntityInstance UUID from MDS*/
        TrackedEntityInstanceMapper instanceMapper = trackedEntityInstanceDataService.
                findByExternalName((String) params.get(EventParams.EXTERNAL_ID));

        /*This is where we would make a call to DHIS2 to get the program details. Here we just hardwire in data*/
        List<String> attributeNames = new ArrayList<String>();
        attributeNames.add("nationalIdentifier");

        List<Attribute> programAttributes = new ArrayList<Attribute>();
        AttributeMapper attributeMapper;

        for (String s : attributeNames) {
            attributeMapper = attributeDataService.findByDhis2Name(s);
            programAttributes.add(new Attribute(attributeMapper.getDhis2Name(),attributeMapper.getDhis2Uuid(),
                    (String)params.get(attributeMapper.getDhis2Name())));
        }
        String date = (String) params.get(EventParams.DATE_REGISTERED);
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
