package org.motechproject.dhis2.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.DefaultHttpClient;

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

    private Logger logger = LoggerFactory.getLogger(HttpService.class);


    /*
    Constructs an HTTP POST request and returns the response
     */
    public HttpEntity send (Request request) {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(request.getUrl());
        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("accept", "application/json");

         //String encoding = Base64Encoder.encode(USERNAME + ":" + PASSWORD);
         //httpPost.setHeader("Authorization", "Basic" + USERNAME + ":" + PASSWORD);

        try {
            StringEntity params = new StringEntity(request.getJsonBody());
            httpPost.setEntity(params);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

           return entity;

        } catch (Exception e) {

            logger.debug("Exception: " +  e.toString());
        }
        return null;
    }
}
