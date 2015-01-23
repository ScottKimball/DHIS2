package org.motechproject.dhis2.service.impl;


import org.motechproject.dhis2.dto.impl.EnrollmentDto;
import org.motechproject.dhis2.http.HttpConstants;
import org.motechproject.dhis2.http.HttpService;
import org.motechproject.dhis2.http.Request;
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

    @Autowired
    public EnrollmentServiceImpl(HttpService httpService) {
        this.httpService = httpService;

    }

    @Override
    public void send(EnrollmentDto enrollmentDto) {

        String body = enrollmentDto.toJson();

        Request request = new Request(HttpConstants.ENROLLMENTS_PATH, body);
        Object entityString = httpService.send(request);

        logger.debug(entityString.toString());


    }


}
