package org.motechproject.dhis2.http;

import com.jayway.jsonpath.Configuration;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by scott on 11/3/14.
 */

@Service
public class HttpQuery {

    private Logger logger = LoggerFactory.getLogger(HttpQuery.class);


    public Object send(Request request) {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(request.getUrl());
        httpGet.addHeader("accept", "application/json");

        try {


            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            String entityString = EntityUtils.toString(entity, "UTF-8");
            Object jsonDocument = Configuration.defaultConfiguration().jsonProvider().parse(entityString);

            return jsonDocument;

        } catch (Exception e) {

            logger.debug("Exception: " + e.toString());
        }
        return null;

    }
}
