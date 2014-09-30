package org.motechproject.dhis2.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by scott on 9/17/14.
 */

@Service
public class HttpService {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "district";
    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(HttpService.class);


    /*
        Constructs an HTTP POST request and returns the response
         */
    public Response send (Request request) {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(request.getUrl());
        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("accept", "application/json");

        try {
            StringEntity params = new StringEntity(request.getJsonBody());
            httpPost.setEntity(params);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            String entityString = EntityUtils.toString(entity, "UTF-8");
            Response response = objectMapper.readValue(entityString, Response.class);

           return response;

        } catch (Exception e) {

            logger.debug("Exception: " +  e.toString());
        }
        return null;
    }
}
