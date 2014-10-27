package org.motechproject.dhis2.service.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.motechproject.dhis2.dto.TrackedEntityInstance;
import org.motechproject.dhis2.http.HttpService;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.http.Response;
import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.dhis2.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by scott on 10/16/14.
 */

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final String URL = "http://admin:district@localhost:8080"; // dhis2 base url
    private static final String TRACKED_ENTITY_PATH = "/api/trackedEntityInstances";
    private Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private HttpService httpService;
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public RegistrationServiceImpl(HttpService httpService,
                                   TrackedEntityInstanceDataService trackedEntityInstanceDataService) {
        this.httpService = httpService;
        this.trackedEntityInstanceDataService = trackedEntityInstanceDataService;
    }

    @Override
    public void send(TrackedEntityInstance trackedEntityInstance) {

        Response response;
        String body = trackedEntityInstance.toJson();

        Request request = new Request(URL + TRACKED_ENTITY_PATH, body);
        String entityString = httpService.send(request);

        try {
            response = objectMapper.readValue(entityString, Response.class);

        } catch (Exception e) {
            logger.debug(e.toString());
            return;
        }

        logger.debug(response.toString());

        TrackedEntityInstanceMapper trackedEntityInstanceMapper =
                new TrackedEntityInstanceMapper(trackedEntityInstance.getExternalId(),response.getReference());
        trackedEntityInstanceDataService.create(trackedEntityInstanceMapper);
        logger.debug("Entity saved.UUID: " + trackedEntityInstanceMapper.getDhis2Uuid() );

    }
}
