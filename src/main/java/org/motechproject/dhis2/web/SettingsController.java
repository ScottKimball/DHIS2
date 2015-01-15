package org.motechproject.dhis2.web;

import org.motechproject.dhis2.service.SyncService;
import org.motechproject.dhis2.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Created by scott on 12/15/14.
 */

@Controller
public class SettingsController {


    private SyncService syncService;
    private TasksService tasksService;

    @Autowired
    public SettingsController(SyncService syncService, TasksService tasksService) {
        this.syncService = syncService;
        this.tasksService = tasksService;
    }


    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void sync() {
        /*TODO save login credentials */

        boolean success = syncService.sync();

        if (success) {
            tasksService.updateChannel();

        }

    }
}
