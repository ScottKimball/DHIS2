package org.motechproject.dhis2.service.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.motechproject.dhis2.domain.Enrollment;
import org.motechproject.dhis2.domain.TrackedEntityInstance;
import org.motechproject.dhis2.http.HttpService;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.http.Response;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.dhis2.service.EnrollmentService;
import org.motechproject.dhis2.service.SendAggregateDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public EnrollmentServiceImpl(HttpService httpService , TrackedEntityInstanceDataService trackedEntityInstanceDataService) {
        this.httpService = httpService;
        this.trackedEntityInstanceDataService = trackedEntityInstanceDataService;
    }

    @Override
    public void send(Enrollment enrollment) {
        Response response;

        // create tracked entity instance in DHIS2
        String body = enrollment.trackedEntityToJson();

        Request request = new Request(URL + TRACKED_ENTITY_PATH, body);
        String entityString = httpService.send(request);

        try {
           response = objectMapper.readValue(entityString, Response.class);

        } catch (Exception e) {
            logger.debug(e.toString());
            return;
        }


        TrackedEntityInstance instance = enrollment.getTrackedEntityInstance();

        if (!response.getStatus().equals("SUCCESS")) {
            logger.debug("Unsuccessful post to DHIS2: " + response);
            return;
        }

        logger.debug(response.toString());
        instance.setDhis2Uuid(response.getReference());
     //   trackedEntityInstanceDataService.create(instance);

        request.setUrl(URL + ENROLLMENT_PATH);
        request.setJsonBody(enrollment.enrollmentToJson());

        entityString = httpService.send(request);

        try {
            response = objectMapper.readValue(entityString, Response.class);

        } catch (Exception e) {
            logger.debug(e.toString());
            return;
        }

        if (!response.getStatus().equals("SUCCESS")) {
            logger.debug("Unsuccessful post to DHIS2: " + response);
            return;
        }

        logger.debug("Successful enrollment: " + response);


    }


}
