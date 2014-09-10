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

import java.util.*;


/**
 * Created by scott on 8/26/14.
 */
@Component
public class EventHandler {

    // hard coded values from Commcare form
    private static final String ORG_UNIT = "OrgUnit";
    private static final String TIME_PERIOD = "timePeriod";
    private static final String NAME = "IntQuestion";
    private static final String VALUE = "value";
    private static final String PARAMS = "additionalParameters";

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
        Map<String, Map> params = (Map<String, Map>) eventParameters.get(PARAMS);
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

            if (temp.equals(NAME) && !gotValue ) {

                do {
                   temp = sc.next();
                    } while (!temp.equals(VALUE));

                value = sc.next();
                gotValue = true;


            } else if (temp.equals(ORG_UNIT) && !gotOrgUnit ) {

                do {
                    temp = sc.next();
                } while (!temp.equals(VALUE));

                orgUnit =sc.next();
                gotOrgUnit = true;

            } else if (temp.equals(TIME_PERIOD)) {

                do {
                    temp = sc.next();
                } while (!temp.equals(VALUE));

                timePeriod = sc.next()  + sc.next() +  sc.next();
                gotTimePeriod = true;

            }
        }

       DataValue dataValue = dataValueService.create(NAME, value, orgUnit, timePeriod);

       return dataValue;
    }

    private Enrollment buildEnrollment(MotechEvent event) {

        logger.debug("In buildEnrollment");

        // TODO:

        return null;
    }

}
