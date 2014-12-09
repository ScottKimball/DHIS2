package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.dto.impl.Stage;
import org.motechproject.dhis2.http.HttpConstants;
import org.motechproject.dhis2.http.HttpService;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.service.StageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by scott on 10/8/14.
 */
@Service
public class StageServiceImpl implements StageService {


    private Logger logger = LoggerFactory.getLogger(StageServiceImpl.class);

    private HttpService httpService;

    @Autowired
    public StageServiceImpl(HttpService httpService) {
        this.httpService = httpService;
    }

    /*TODO: transition from string checking to JsonPath*/
    @Override
    public void send(Stage stage) {

        String body = stage.toJson();
        Request request = new Request(HttpConstants.EVENTS_PATH, body);
        String entityString =  httpService.send(request);

        if (entityString.contains("SUCCESS"))
            logger.debug("Successful update to DHIS2: " + entityString);

        else
            logger.debug("Failure to update to DHIS2: " + entityString);



    }
}
