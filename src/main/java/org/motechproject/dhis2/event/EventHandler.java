package org.motechproject.dhis2.event;

import org.joda.time.DateTime;
import org.motechproject.dhis2.domain.*;
import org.motechproject.dhis2.repository.ProgramDataService;
import org.motechproject.dhis2.service.EnrollmentService;
import org.motechproject.dhis2.service.SendAggregateDataService;
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

    private SendAggregateDataService sendAggregateDataService;
    private Logger logger = LoggerFactory.getLogger(EventHandler.class);
    private EventRelay eventRelay;
    private EnrollmentService enrollmentService;
    private ProgramDataService programDataService;
    private StageService stageService;


    @Autowired
    public EventHandler( EventRelay eventRelay,
                         SendAggregateDataService sendAggregateDataService,
                         EnrollmentService enrollmentService, ProgramDataService programDataService,
                         StageService stageService
                        ){
        this.eventRelay = eventRelay;
        this.sendAggregateDataService = sendAggregateDataService;
        this.enrollmentService = enrollmentService;
        this.programDataService = programDataService;
        this.stageService = stageService;


    }


    @MotechListener(subjects  = {EventSubjects.TB_REGISTRATION})
    public void handleTbRegistration (MotechEvent event) {
        logger.debug("In TB Registration handler");
        Enrollment enrollment = buildEnrollment(event);
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
        Program program = new Program(formName,"TB Program", programUUID,new TrackedEntity("Person"),null );

        /* This is where we will query mds for for the trackedEntityInstance. Right now we just
         create a new tracked entity instance */
        TrackedEntityInstance instance = new TrackedEntityInstance(commcareId,
                new TrackedEntity("Person") ,INSTANCE_UUID, null,new OrgUnit(null,null,orgUnitUUID));

        Stage stage = new Stage(formName,STAGE_NAME,stage_uuid,program,null,followUpDate,instance,instance.getOrgUnit());
        stageService.send(stage);

        }



    /* Ths method builds an enrollment object from a motech event. Right now, it builds new entities on the fly
     * but eventually the following sequence will occur:
      * 1.  Get the form name
      * 2. Query mds to get the list of form fields for that form
      * 3. Pull the form field values off the event
      * 4. Query mds for program associated with the form case type
      * 5. Query mds for the Tracked entity associated with the program
      * 6. Create a new tracked entity instance (instance)
      *     a. For each Tracked entity required attribute
      *         1. add a new attribute to instance.attributeList with associated form field value
      *     b. at this point, uuid is null. Will have to wait for interacting with dhis2 to get uuid
      * 7. query mds for associated org unit
      * 8. get date from event parameters
      * 9. construct new enrollment (program , date. orgUnit, instance)
       * */
    private Enrollment buildEnrollment (MotechEvent event) {

        String commcareUUID =(String) event.getParameters().get(EventParams.CASE_ID);  // case_ID
        Map<String , Object> params = event.getParameters();

        // create tracked entity
        TrackedEntity trackedEntity = new TrackedEntity("Person");
        trackedEntity.setDhis2Uuid(trackedEntityUUID);


        //Required Attribute for program
        Attribute programRequiredAttribute = new Attribute("nationalIdentifier",nationalIdentifierUUID,null);

        // list for programattribute list
        List<Attribute> programAttributeList = new ArrayList<Attribute>();
        programAttributeList.add(programRequiredAttribute);

        // create program
        Program program = new Program((String) params.get(EventParams.CASE_TYPE) ,"TB Visit" ,programUUID,
                trackedEntity , programAttributeList);


        // pulls form fields off event parameters and adds them to tracked entity instance attribute list
        List<Attribute> attributeList = new ArrayList<Attribute>();

        attributeList.add( new Attribute("lastName", lastNameUUID, (String) params.get(EventParams.LAST_NAME)));
        attributeList.add(new Attribute("firstName", firstNameUUID, (String) params.get(EventParams.FIRST_NAME)));
        attributeList.add(new Attribute("gender", genderUUID, (String) params.get(EventParams.LAST_NAME)));
        attributeList.add(new Attribute("nationalIdentifier",nationalIdentifierUUID,
                (String) params.get(EventParams.NATIONAL_IDENTIFIER)));

        TrackedEntityInstance trackedEntityInstance = new TrackedEntityInstance(commcareUUID,trackedEntity,
                attributeList);

        // create org unit
        OrgUnit orgUnit = new OrgUnit((String) params.get(EventParams.LOCATION) ,
                (String) params.get(EventParams.LOCATION),orgUnitUUID);

        // create date
        DateTime date = new DateTime();
        DateTime.parse((String)params.get(EventParams.DATE_REGISTERED));

        Enrollment enrollment = new Enrollment(program,trackedEntityInstance,orgUnit,date );

        return enrollment;
    }

}
