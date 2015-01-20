package org.motechproject.dhis2.http;

import com.jayway.jsonpath.Configuration;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by scott on 9/17/14.
 */

@Service
public class HttpService {


    private Logger logger = LoggerFactory.getLogger(HttpService.class);


    /*  Constructs an HTTP POST request and returns the response  */
    public Object send(Request request) {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(request.getUrl());
        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("accept", "application/json");


        try {
            StringEntity params = new StringEntity(request.getBody());
            httpPost.setEntity(params);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            String entityString = EntityUtils.toString(entity, "UTF-8");


            return Configuration.defaultConfiguration().jsonProvider().parse(entityString);

        } catch (Exception e) {

            logger.debug("Exception: " + e.toString());
        }

        return null;

    }
}
