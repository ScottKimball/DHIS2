package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.domain.DataValue;
import org.motechproject.dhis2.service.SendAggregateDataService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by scott on 8/29/14.
 */

@Service
public class SendAggregateDataServiceImpl implements SendAggregateDataService {

    private Logger logger = LoggerFactory.getLogger(SendAggregateDataService.class);


    public void send(DataValue dataValue) {
        logger.debug("In SendAggregateDataServiceImpl");
        logger.debug("Data value:\n  " + dataValue);

        // TODO:

    }
}
