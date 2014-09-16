package org.motechproject.dhis2.event;

import org.motechproject.dhis2.domain.*;
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

import java.awt.*;
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

    @Autowired
    public EventHandler( EventRelay eventRelay,
                         SendAggregateDataService sendAggregateDataService, DataValueService dataValueService, EnrollmentService enrollmentService ){
        this.eventRelay = eventRelay;
        this.sendAggregateDataService = sendAggregateDataService;
        this.dataValueService = dataValueService;
        this.enrollmentService = enrollmentService;
    }

    @MotechListener(subjects = {EventSubjects.ENROLL_IN_PROGRAM})
    public void handleEnrollment (MotechEvent event) {

        logger.debug("Recieved enrollment event :" );
        logger.debug(event.toString());

        Enrollment enrollment = buildEnrollment(event);
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

    private Enrollment buildEnrollment(MotechEvent event) {


        logger.debug("In buildEnrollment");

        Map<String, Object> eventParameters =  event.getParameters();
        Map<String, Map> params = (Map<String, Map>) eventParameters.get("subElements");
        String s = params.toString();

        Scanner sc = new Scanner (s).useDelimiter("[^a-zA-Z0-9&&[^_-]]");
        String lastName = null,firstName = null, gender = null,
                location = null, nationalIdentifier = null, caseId = null,
                caseType = null, dateRegistered = null;
        boolean gotLastName = false, gotFirstName = false , gotGender = false, gotLocation = false,
        gotNationalIdentifier = false, gotCaseId = false, gotCaseType = false, gotDateRegistered = false;


        while (sc.hasNext() && !gotCaseType ) {
            String temp = sc.next();

            if (temp.equals(EventParams.LAST_NAME) && !gotLastName ) {
                lastName = findValue(sc);
                gotLastName = true;

            } else if (temp.equals(EventParams.FIRST_NAME) && !gotFirstName ) {
                firstName = findValue(sc);
                gotFirstName = true;

            } else if (temp.equals(EventParams.GENDER) && !gotGender) {
                gender = findValue(sc);
                gotGender = true;

            } else if (temp.equals(EventParams.LOCATION) && !gotLocation) {
                location = findValue(sc);
                gotLocation = true;

            } else if (temp.equals(EventParams.NATIONAL_IDENTIFIER) && !gotNationalIdentifier) {
                nationalIdentifier = findValue(sc);
                gotNationalIdentifier = true;

            } else if (temp.equals(EventParams.DATE_REGISTERED) && !gotDateRegistered) {
                dateRegistered = findValue(sc);
                gotDateRegistered = true;

            } else if (temp.equals(EventParams.CASE_ID) && !gotCaseId) {
                caseId = sc.next();
                gotCaseId = true;

            } else if (temp.equals(EventParams.CASE_TYPE)) {
                caseType = findValue(sc);
                gotCaseType = true;
            }
        }



        // TODO:

        return null;
    }

    private String findValue (Scanner scanner ) {
        String temp;
        do {
            temp = scanner.next();
        } while (!temp.equals(EventParams.VALUE));

        return scanner.next();

    }

}
