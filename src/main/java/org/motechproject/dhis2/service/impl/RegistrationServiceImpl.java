package org.motechproject.dhis2.service.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.http.HttpConstants;
import org.motechproject.dhis2.http.HttpService;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.http.Response;
import org.motechproject.dhis2.domain.Mapper.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.repository.Mapper.TrackedEntityInstanceDataService;
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

    /*TODO: transition from ObjectMapper to JsonPath*/
    @Override
    public void send(TrackedEntityInstanceDto trackedEntityInstanceDto) {

        Response response;
        String body = trackedEntityInstanceDto.toJson();

        Request request = new Request(HttpConstants.TRACKED_ENTITY_INSTANCES_PATH, body);
        String entityString = httpService.send(request);

        try {
            response = objectMapper.readValue(entityString, Response.class);

        } catch (Exception e) {
            logger.debug(e.toString());
            return;
        }

        logger.debug(response.toString());

        TrackedEntityInstanceMapper trackedEntityInstanceMapper = trackedEntityInstanceDataService.
                findByExternalName(trackedEntityInstanceDto.getExternalId() );

        if (trackedEntityInstanceMapper != null) {
            logger.debug("Entity updated.\nUUID: " + trackedEntityInstanceMapper.getDhis2Uuid() );

        } else {
            trackedEntityInstanceMapper =
                    new TrackedEntityInstanceMapper(trackedEntityInstanceDto.getExternalId(),response.getReference());

            trackedEntityInstanceDataService.create(trackedEntityInstanceMapper);
            logger.debug("Entity saved.\nUUID: " + trackedEntityInstanceMapper.getDhis2Uuid() );

        }

    }
}
