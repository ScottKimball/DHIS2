package org.motechproject.dhis2.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.motechproject.dhis2.domain.DataValue;
import org.motechproject.dhis2.service.SendAggregateDataService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by scott on 8/29/14.
 */

@Service
public class SendAggregateDataServiceImpl implements SendAggregateDataService {

    private Logger logger = LoggerFactory.getLogger(SendAggregateDataService.class);

    private static final String URI = "localhost:8080/";    // hardcoded URI


    public void send(DataValue dataValue)  {

        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpPost request = new HttpPost(URI);
        request.addHeader("content-type", "application/x-www-form-urlencoded");

        try {
            String paramString =  "{ \"dataSet\" : \"" + dataValue.getDataSet() + "\", " +
                    "\"period\" : \"" + dataValue.getTimePeriod() + "\", " +
                    "\"orgUnit\" : \"" + dataValue.getOrgUnitUUID() + "\", " +
                    "\"dataValues\" : [ { " +
                    "\"dataElement\" : \"" + dataValue.getDataElementUUID() + "\", " +
                    "\"value\" : \"" + dataValue.getValue() + "\" } ] }";

            StringEntity params = new StringEntity(paramString);
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            logger.debug(response.toString());

        } catch (Exception e) {
            logger.debug(e.toString());
        }


    }

}
