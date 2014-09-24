package org.motechproject.dhis2.service.impl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.motechproject.dhis2.domain.Enrollment;
import org.motechproject.dhis2.domain.TrackedEntityInstance;
import org.motechproject.dhis2.http.HttpService;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.ProgramDataService;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.dhis2.service.EnrollmentService;
import org.motechproject.dhis2.service.SendAggregateDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;

/**
 * Created by scott on 9/10/14.
 */

// TODO:
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private static final String URL = "http://admin:district@localhost:8080"; // dhis2 base url
    private static final String TRACKED_ENTITY_PATH = "/api/trackedEntityInstances";
    private static final String ENROLLMENT_PATH = "/api/enrollments";
    private Logger logger = LoggerFactory.getLogger(SendAggregateDataService.class);

    private HttpService httpService;
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;

    @Autowired
    public EnrollmentServiceImpl(HttpService httpService , TrackedEntityInstanceDataService trackedEntityInstanceDataService) {
        this.httpService = httpService;
        this.trackedEntityInstanceDataService = trackedEntityInstanceDataService;
    }

    @Override
    public void send(Enrollment enrollment) {

        // create tracked entity instance in DHIS2
        String body = enrollment.trackedEntityToJson();

        Request request = new Request(URL + TRACKED_ENTITY_PATH, body);
        HttpEntity response = httpService.send(request);

        try {
            String entityString = EntityUtils.toString(response, "UTF-8");
            logger.debug("In EnrollmentServiceImpl.send:" + entityString);

        } catch (IOException e) {
            logger.debug(e.toString());
        }


        logger.debug(response.toString());

        //TODO : Get UUID from http response
        /*

        // set UUID and then persist the instance
        String uuid = "trackedEntityInstanceUUID";
        enrollment.getTrackedEntityInstance().setUUID(uuid);

        body = enrollment.enrollmentToJson();
        request = new Request(URL + ENROLLMENT_PATH , body );
        response = httpService.send(request);
    */


    }




}
