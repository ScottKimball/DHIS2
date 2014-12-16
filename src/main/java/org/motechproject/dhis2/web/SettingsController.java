package org.motechproject.dhis2.web;

import org.motechproject.dhis2.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



/**
 * Created by scott on 12/15/14.
 */

@Controller
public class SettingsController {


    private SyncService syncService;

    @Autowired
    public SettingsController( SyncService syncService) {
        this.syncService = syncService;
    }


    @RequestMapping(value = "/sync" , produces = "application/json" ,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void sync() {
       boolean success = syncService.sync();

        // "\"success\":" + success;
    }
}
