package org.motechproject.dhis2.service.impl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.motechproject.dhis2.domain.Enrollment;
import org.motechproject.dhis2.http.HttpService;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.ProgramDataService;
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

    private static final String URL = ""; // dhis2 endpoint
    private Logger logger = LoggerFactory.getLogger(SendAggregateDataService.class);

    private HttpService httpService;

    @Autowired
    public EnrollmentServiceImpl(HttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    public void send(Enrollment enrollment) {

        //TODO :

        String body = enrollment.toJson();

        Request request = new Request(URL, body);
        HttpEntity response = httpService.send(request);

    }




}
