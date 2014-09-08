package org.motechproject.dhis2.service.impl;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.motechproject.dhis2.domain.DataValue;
import org.motechproject.dhis2.service.SendAggregateDataService;
import org.omg.CORBA.NameValuePair;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scott on 8/29/14.
 */

@Service
public class SendAggregateDataServiceImpl implements SendAggregateDataService {

    private Logger logger = LoggerFactory.getLogger(SendAggregateDataService.class);

    private static final String URI = "localhost:8080/";


    public void send(DataValue dataValue) {
        logger.debug("In SendAggregateDataServiceImpl");
        logger.debug(dataValue.toString());

    }

}
