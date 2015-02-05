package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.domain.Settings;
import org.motechproject.dhis2.dto.Dto;
import org.motechproject.dhis2.http.HttpService;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.service.DataTransferService;
import org.motechproject.dhis2.service.SettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataTransferServiceImpl implements DataTransferService {

    private static final String SUCCESS = "SUCCESS";
    private Logger logger = LoggerFactory.getLogger(DataTransferServiceImpl.class);

    private HttpService httpService;
    private SettingsService settingsService;

    @Autowired
    public DataTransferServiceImpl(HttpService httpService,
                                   @Qualifier("dhisSettingsService") SettingsService settingsService) {
        this.httpService = httpService;
        this.settingsService = settingsService;
    }

    @Override
    public void send(Dto dto, String path) {

        String body = null;
        try {
            body = dto.toJson();

        } catch (IOException e) {
            logger.error("Data transfer failed");
            logger.error(e.toString());
            return;

        }

        Request request = new Request(path, body);
        Settings settings =  settingsService.getSettings();
        Object response = httpService.send(request, settings.getUsername(), settings.getPassword());
        String responseString = response.toString();

        if (responseString.contains(SUCCESS)) {
            logger.debug("Successful data transfer");
            logger.debug(responseString);

        } else {
            logger.error("Data transfer failed");
            logger.error("dto information:" + body);
            logger.error(responseString);

        }

    }
}
