package org.motechproject.dhis2.service.impl;

import com.jayway.jsonpath.JsonPath;
import org.motechproject.dhis2.domain.Settings;
import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.http.HttpService;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.dhis2.service.InstanceCreationService;
import org.motechproject.dhis2.service.SettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InstanceCreationServiceImpl implements InstanceCreationService {

    private Logger logger = LoggerFactory.getLogger(InstanceCreationServiceImpl.class);

    private HttpService httpService;
    private SettingsService settingsService;
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;

    private static final String STATUS = "$.status";
    private static final String REFERENCE = "$.reference";
    private static final String SUCCESS = "SUCCESS";

    @Autowired
    public InstanceCreationServiceImpl(HttpService httpService,
                                       TrackedEntityInstanceDataService trackedEntityInstanceDataService,
                                       @Qualifier("dhisSettingsService") SettingsService settingsService) {
        this.httpService = httpService;
        this.settingsService = settingsService;
        this.trackedEntityInstanceDataService = trackedEntityInstanceDataService;
    }

    /*TODO transition from ObjectMapper to JsonPath*/
    @Override
    public void send(TrackedEntityInstanceDto trackedEntityInstanceDto, String path) {


        try {

            String body = trackedEntityInstanceDto.toJson();

            Request request = new Request(path, body);
            Settings settings = settingsService.getSettings();
            Object response = httpService.send(request, settings.getUsername(), settings.getPassword());
            String status = JsonPath.read(response, STATUS);

            if (status.equals(SUCCESS)) {

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

                logger.error("Problem with tracked Entity Instance Creation. Data transfer failed");
                logger.error(response.toString());
            }


        } catch (Exception e) {
            logger.error("Problem with tracked Entity Instance Creation. Data transfer failed");
            logger.error(e.toString());
        }
    }
}
