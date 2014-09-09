package org.motechproject.dhis2.service.impl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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

    private static final String URI = "http://admin:district@localhost:8080/api/dataValueSets";    // hardcoded URI


    public void send(DataValue dataValue)  {

        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpPost request = new HttpPost(URI);
        request.addHeader("content-type", "application/json");

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
            HttpEntity entity = response.getEntity();
            String entityString = EntityUtils.toString(entity, "UTF-8");
            logger.debug("Response : " + entityString );

        } catch (Exception e) {
            logger.debug(e.toString());
        }


    }

}
