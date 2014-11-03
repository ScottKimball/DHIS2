package org.motechproject.dhis2.event;

import org.joda.time.DateTime;
import org.motechproject.dhis2.domain.*;
import org.motechproject.dhis2.dto.*;
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


    @Autowired
    public EventHandler( EventRelay eventRelay,
                         EnrollmentService enrollmentService, ProgramDataService programDataService,
                         StageService stageService,
                         TrackedEntityDataService trackedEntityDataService,
                         OrgUnitDataService orgUnitDataService,
                         RegistrationService registrationService,
                         AttributeDataService attributeDataService,
                         TrackedEntityInstanceDataService trackedEntityInstanceDataService
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

        String externalUUID =(String) params.get(EventParams.EXTERNAL_ID);

        /*TODO: Replace with call to DHIS2 */
        TrackedEntityMapper trackedEntityMapper = trackedEntityDataService.findByExternalName("Person");
        String trackedEntity = trackedEntityMapper.getDhis2Uuid();


        /*This is where we will make a call to DHIS2 to get the list of attributes for the program
        * and then iterate down the list and pull the corresponding fields off of the event*/

        List<Attribute> attributeList = new ArrayList<Attribute>();

        AttributeMapper lastName = attributeDataService.findByDhis2Name("lastName");
        attributeList.add(new Attribute(lastName.getDhis2Name(),lastName.getDhis2Uuid(),
                (String) params.get(EventParams.LAST_NAME)));

        AttributeMapper firstName = attributeDataService.findByDhis2Name("firstName");
        attributeList.add(new Attribute(firstName.getDhis2Name(),firstName.getDhis2Uuid(),
                (String)params.get(EventParams.FIRST_NAME)));

        AttributeMapper gender = attributeDataService.findByDhis2Name("gender");
        attributeList.add(new Attribute(gender.getDhis2Name(),gender.getDhis2Uuid(),
                (String)params.get(EventParams.GENDER)));


        OrgUnitMapper orgUnitMapper = orgUnitDataService.findByExternalName((String) params.get(EventParams.LOCATION));

        String orgUnit = orgUnitMapper.getDhis2Uuid();
        TrackedEntityInstance instance = new TrackedEntityInstance(externalUUID,trackedEntity,attributeList,orgUnit);

        registrationService.send(instance);
    }


    @MotechListener(subjects  = {EventSubjects.TB_ENROLL})
    public void handleTbRegistration (MotechEvent event) {

        Map<String,Object> params =  event.getParameters();


        ProgramMapper programMapper = programDataService.findByExternalName((String)params.get(params.get(EventParams.PROGRAM)));

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
        final String STAGE_NAME = "TB Follow Up";
        final String INSTANCE_UUID = "IzHblRD2sDH";

        logger.debug("In Handle Tb follow up");

        Map<String,Object> params = event.getParameters();

        String followUpDate = (String) params.get("followUpDate");

        // had to hardwire in value in tasks as field is not currently exposed
        String commcareId =(String) params.get("caseId");

        /* We will have to query mds for form information by looking up the form fields for the particular
        form in MDS. Right now we just create new Program information. */
        String formName = event.getSubject();
        Program program = new Program(formName,"TB Program", programUUID,new TrackedEntity("Person",null),null );

        /* This is where we will query mds for for the trackedEntityInstance. Right now we just
         create a new tracked entity instance */
      //  TrackedEntityInstance instance = new TrackedEntityInstance(commcareId,
        //        new TrackedEntity("Person",null) ,INSTANCE_UUID, null,new OrgUnit(null,orgUnitUUID));

     //   Stage stage = new Stage(formName,STAGE_NAME,stage_uuid,program,null,followUpDate,instance,instance.getOrgUnit());
     //   stageService.send(stage);

        }



}
