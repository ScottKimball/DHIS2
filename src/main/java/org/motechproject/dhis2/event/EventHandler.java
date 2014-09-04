package org.motechproject.dhis2.event;

import org.motechproject.dhis2.domain.DataValue;
import org.motechproject.dhis2.domain.IndividualRecord;
import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.service.SendAggregateDataService;
import org.motechproject.event.listener.EventRelay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.motechproject.dhis2.service.SendIndividualRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by scott on 8/26/14.
 */
@Component
public class EventHandler {

    private static final String ORG_UNIT = "orgUnit";
    private static final String TIME_PERIOD = "timePeriod";
    private static final String VALUE = "Int_Question";
    private static final String PARAMS = "additionalParameters";

    private SendIndividualRecordService sendIndividualRecordService;
    private SendAggregateDataService sendAggregateDataService;
    private Logger logger = LoggerFactory.getLogger(EventHandler.class);
    private EventRelay eventRelay;

    @Autowired
    public EventHandler( EventRelay eventRelay, SendIndividualRecordService sendIndividualRecordService,
                         SendAggregateDataService sendAggregateDataService) {
        this.sendIndividualRecordService = sendIndividualRecordService;
        this.eventRelay = eventRelay;
        this.sendAggregateDataService = sendAggregateDataService;
    }

    @MotechListener(subjects = {EventSubjects.SEND_INDIVIDUAL_RECORD})
    public void handleIndividualRecord (MotechEvent event) {

        logger.debug("Recieved Individual Record event :" );
        logger.debug(event.toString());

        // TODO:


    }

    @MotechListener(subjects = {EventSubjects.SEND_AGGREGATE_DATA})
    public void handleAggregateData (MotechEvent event) {
        logger.debug("Recieved Aggregate Data Event");
        logger.debug("Event details: " + event);
        DataValue dataValue = parseAggregateData(event);
       sendAggregateDataService.send(dataValue);
    }

    private DataValue parseAggregateData (MotechEvent event) {
        DataValue dataValue;
        Map <String ,Map> parameters = (Map<String, Map>) event.getParameters().get(PARAMS);
        Map <String, String> orgUnitMap = parameters.get(ORG_UNIT);
        Map<String, String> timeMap = parameters.get(TIME_PERIOD);
        //Map <String, String>

        //TODO: Add data element domain object ( name, uuid)



        return null;
    }

    private IndividualRecord parseIndividualRecord (MotechEvent event) {
        // TODO:

        return null;
    }

}
