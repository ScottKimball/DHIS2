package org.motechproject.dhis2.service.impl;


import com.jayway.jsonpath.JsonPath;
import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.http.HttpConstants;
import org.motechproject.dhis2.http.HttpService;
import org.motechproject.dhis2.http.Request;
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

    private Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private HttpService httpService;
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;

    private static final String STATUS = "$.status";
    private static final String REFERENCE = "$.reference";
    private static final String SUCCESS = "SUCCESS";

    @Autowired
    public RegistrationServiceImpl(HttpService httpService,
                                   TrackedEntityInstanceDataService trackedEntityInstanceDataService) {
        this.httpService = httpService;
        this.trackedEntityInstanceDataService = trackedEntityInstanceDataService;
    }

    /*TODO transition from ObjectMapper to JsonPath*/
    @Override
    public void send(TrackedEntityInstanceDto trackedEntityInstanceDto) {


        String body = trackedEntityInstanceDto.toJson();

        Request request = new Request(HttpConstants.TRACKED_ENTITY_INSTANCES_PATH, body);
        Object response = httpService.send(request);
        String status = JsonPath.read(response, STATUS);
        if (status.equalsIgnoreCase(SUCCESS)) {

            logger.debug(response.toString());

            String reference = JsonPath.read(response, REFERENCE);

            TrackedEntityInstanceMapper trackedEntityInstanceMapper = trackedEntityInstanceDataService.
                    findByExternalName(trackedEntityInstanceDto.getExternalId());

            if (trackedEntityInstanceMapper != null) {
                logger.debug("Entity updated.\nUUID: " + trackedEntityInstanceMapper.getDhis2Uuid());

            } else {
                trackedEntityInstanceMapper =
                        new TrackedEntityInstanceMapper(trackedEntityInstanceDto.getExternalId(), reference);

                trackedEntityInstanceDataService.create(trackedEntityInstanceMapper);
                logger.debug("Entity saved.\nUUID: " + trackedEntityInstanceMapper.getDhis2Uuid());

            }
        } else {

            logger.error("Problem with tracked Entity Instance Creation");
        }



    }
}
