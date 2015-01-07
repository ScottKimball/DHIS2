package org.motechproject.dhis2.service.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.motechproject.dhis2.dto.impl.EnrollmentDto;
import org.motechproject.dhis2.http.HttpConstants;
import org.motechproject.dhis2.http.HttpService;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.http.Response;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.dhis2.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by scott on 9/10/14.
 */

@Service
public class EnrollmentServiceImpl implements EnrollmentService {


    private Logger logger = LoggerFactory.getLogger(EnrollmentServiceImpl.class);

    private HttpService httpService;
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public EnrollmentServiceImpl(HttpService httpService , TrackedEntityInstanceDataService trackedEntityInstanceDataService) {
        this.httpService = httpService;
        this.trackedEntityInstanceDataService = trackedEntityInstanceDataService;
    }

    /*TODO: transition from ObjectMapper to JsonPath*/
    @Override
    public void send(EnrollmentDto enrollmentDto) {

        Response response;
        String body = enrollmentDto.toJson();

        Request request = new Request(HttpConstants.ENROLLMENTS_PATH, body);
        String entityString = httpService.send(request);

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
