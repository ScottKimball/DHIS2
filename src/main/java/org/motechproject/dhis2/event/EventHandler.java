package org.motechproject.dhis2.event;

import org.joda.time.DateTime;
import org.motechproject.dhis2.domain.*;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.ProgramDataService;
import org.motechproject.dhis2.service.DataValueService;
import org.motechproject.dhis2.service.EnrollmentService;
import org.motechproject.dhis2.service.SendAggregateDataService;
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




    private SendAggregateDataService sendAggregateDataService;
    private Logger logger = LoggerFactory.getLogger(EventHandler.class);
    private EventRelay eventRelay;
    private DataValueService dataValueService;
    private EnrollmentService enrollmentService;
    private ProgramDataService programDataService;
    private OrgUnitDataService orgUnitDataService;


    @Autowired
    public EventHandler( EventRelay eventRelay,
                         SendAggregateDataService sendAggregateDataService, DataValueService dataValueService,
                         EnrollmentService enrollmentService, ProgramDataService programDataService ,
                         OrgUnitDataService orgUnitDataService
                        ){
        this.eventRelay = eventRelay;
        this.sendAggregateDataService = sendAggregateDataService;
        this.dataValueService = dataValueService;
        this.enrollmentService = enrollmentService;
        this.programDataService = programDataService;
        this.orgUnitDataService = orgUnitDataService;

    }

    @MotechListener(subjects = {EventSubjects.ENROLL_IN_PROGRAM})
    public void handleEnrollment (MotechEvent event) {

        logger.debug("Recieved enrollment event :" );
        logger.debug(event.toString());

       MotechEvent newEvent = parseEnrollmentData(event);
        Enrollment enrollment = buildEnrollment(newEvent);
        enrollmentService.send(enrollment);

    }

    @MotechListener(subjects = {EventSubjects.SEND_AGGREGATE_DATA})
    public void handleAggregateData (MotechEvent event) throws Exception{
        logger.debug("Recieved Aggregate Data Event");
        logger.debug("Event details: " + event);

        DataValue dataValue = parseAggregateData(event);
        sendAggregateDataService.send(dataValue);
    }

    private DataValue parseAggregateData (MotechEvent event) {
        Map<String, Object> eventParameters =  event.getParameters();
        Map<String, Map> params = (Map<String, Map>) eventParameters.get(EventParams.PARAMS);
        String s = params.toString();

        Scanner sc = new Scanner (s).useDelimiter("[^a-zA-Z0-9]");
        String value = null;
        String orgUnit = null;
        String timePeriod = null;
        boolean gotValue = false;
        boolean gotOrgUnit = false;
        boolean gotTimePeriod = false;

        while (sc.hasNext() && !gotTimePeriod ) {
            String temp = sc.next();

            if (temp.equals(EventParams.NAME) && !gotValue ) {

                do {
                   temp = sc.next();
                    } while (!temp.equals(EventParams.VALUE));

                value = sc.next();
                gotValue = true;


            } else if (temp.equals(EventParams.ORG_UNIT) && !gotOrgUnit ) {

                do {
                    temp = sc.next();
                } while (!temp.equals(EventParams.VALUE));

                orgUnit =sc.next();
                gotOrgUnit = true;

            } else if (temp.equals(EventParams.TIME_PERIOD)) {

                do {
                    temp = sc.next();
                } while (!temp.equals(EventParams.VALUE));

                timePeriod = sc.next()  + sc.next() +  sc.next();
                gotTimePeriod = true;

            }
        }

       DataValue dataValue = dataValueService.create(EventParams.NAME, value, orgUnit, timePeriod);

       return dataValue;
    }

    /*
    Parses the malformed event and creates new correctly formed event
     */
    private MotechEvent parseEnrollmentData(MotechEvent event) {

        Map<String, Object> eventParameters =  event.getParameters();
        Map<String, Map> params = (Map<String, Map>) eventParameters.get("subElements");
        String s = params.toString();

        Scanner sc = new Scanner (s).useDelimiter("[^a-zA-Z0-9&&[^_-]]");
        String lastName = null,firstName = null, gender = null,
                location = null, nationalIdentifier = null, caseId = null,
                caseType = null, dateRegistered = null;

        while (sc.hasNext() && caseType == null ) {
            String temp = sc.next();

            if (temp.equals(EventParams.LAST_NAME) && lastName == null ) {
                lastName = findValue(sc);

            } else if (temp.equals(EventParams.FIRST_NAME) && firstName == null ) {
                firstName = findValue(sc);

            } else if (temp.equals(EventParams.GENDER) && gender == null) {
                gender = findValue(sc);

            } else if (temp.equals(EventParams.LOCATION) && location == null) {
                location = findValue(sc);

            } else if (temp.equals(EventParams.NATIONAL_IDENTIFIER) && nationalIdentifier == null) {
                nationalIdentifier = findValue(sc);

            } else if (temp.equals(EventParams.DATE_REGISTERED) && dateRegistered == null) {
                dateRegistered = findValue(sc);

            } else if (temp.equals(EventParams.CASE_ID) && caseId == null) {
                caseId = sc.next();

            } else if (temp.equals(EventParams.CASE_TYPE)) {
                caseType = findValue(sc);
            }
        }


        // builds new event that looks more like a correctly formed event
        Map <String , Object> paramMap = new HashMap<String, Object>();
        paramMap.put(EventParams.LAST_NAME , lastName);
        paramMap.put(EventParams.FIRST_NAME, firstName);
        paramMap.put(EventParams.GENDER, gender);
        paramMap.put(EventParams.LOCATION,location);
        paramMap.put(EventParams.NATIONAL_IDENTIFIER,nationalIdentifier);
        paramMap.put(EventParams.DATE_REGISTERED,dateRegistered);
        paramMap.put(EventParams.CASE_ID,caseId);
        paramMap.put(EventParams.CASE_TYPE,caseType);

        MotechEvent newEvent = new MotechEvent(EventSubjects.ENROLL_IN_PROGRAM , paramMap);

        return newEvent;
    }


    /* right now we know which form fields we have and
     and I'm creating the entities on the fly rather than getting them from mds */
    private Enrollment buildEnrollment (MotechEvent event) {

        String trackedEntityUUID = "temp tracked entity UUID"; // uuid for person tracked entity
        String commcareUUID = "temp commcare UUID" ; // commcare provides uuid for each case instance
        String programUUID = "temp program UUID";
        String orgUnitUUID = "temp org unit UUID";

        Map<String , Object> params = event.getParameters();

        // create tracked entity
        TrackedEntity trackedEntity = new TrackedEntity("Person");
        trackedEntity.setUUID(trackedEntityUUID);

        // create program
        Program program = new Program((String) params.get(EventParams.CASE_TYPE) ,"TB Visit" ,programUUID,
                trackedEntity);


        // attributes for tracked entity instance
        Map <String ,String> attributeMap = new HashMap<String, String>();
        attributeMap.put("lastName",(String) params.get(EventParams.LAST_NAME));
        attributeMap.put("firstName" ,(String) params.get(EventParams.FIRST_NAME));
        attributeMap.put("gender" ,(String) params.get(EventParams.GENDER));
        attributeMap.put("nationalIdentifier" , (String) params.get(EventParams.NATIONAL_IDENTIFIER));

        // create tracked entity instance
        TrackedEntityInstance trackedEntityInstance = new TrackedEntityInstance(commcareUUID,trackedEntity,
                attributeMap);

        // create org unit
        OrgUnit orgUnit = new OrgUnit((String) params.get(EventParams.LOCATION) ,
                (String) params.get(EventParams.LOCATION),orgUnitUUID);

        DateTime date = new DateTime();
        DateTime.parse((String)params.get(EventParams.DATE_REGISTERED));

        Enrollment enrollment = new Enrollment(program,trackedEntityInstance, orgUnit,date );

        return enrollment;
    }


    private String findValue (Scanner scanner ) {
        String temp;
        do {
            temp = scanner.next();
        } while (!temp.equals(EventParams.VALUE));

        return scanner.next();

    }

}
