package org.motechproject.dhis2.web;

import org.motechproject.dhis2.domain.Settings;
import org.motechproject.dhis2.service.SettingsService;
import org.motechproject.dhis2.service.SyncService;
import org.motechproject.dhis2.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class DhisSettingsController {
    private SettingsService settingsService;
    private SyncService syncService;
    private TasksService tasksService;

    @Autowired
    public DhisSettingsController(@Qualifier("dhisSettingsService") SettingsService settingsService,
                                  SyncService syncService,
                                  TasksService tasksService) {
        this.settingsService = settingsService;
        this.syncService = syncService;
        this.tasksService = tasksService;
    }

    @RequestMapping(value = "/dhis2-settings", method = RequestMethod.GET)
    @ResponseBody
    public Settings getSettings() {
        return settingsService.getSettings();
    }

    @RequestMapping(value = "/dhis2-settings", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Settings updateSettings(@RequestBody Settings settings) {
        settingsService.updateSettings(settings);
        return settingsService.getSettings();
    }

    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public boolean sync() {

        boolean success = syncService.sync();

        if (success) {
            tasksService.updateChannel();
        }

        return success;
    }
}
