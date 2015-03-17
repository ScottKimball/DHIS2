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

/**
 * The controller that handles both changes to module settings and syncing to the DHIS2 server.
 * @see org.motechproject.dhis2.service.SyncService
 * @see org.motechproject.dhis2.service.SettingsService
 */
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

    /**
     * Returns the settings for the module
     * @return an {@link org.motechproject.dhis2.domain.Settings} object for the DHIS2 module
     */
    @RequestMapping(value = "/dhis2-settings", method = RequestMethod.GET)
    @ResponseBody
    public Settings getSettings() {
        return settingsService.getSettings();
    }

    /**
     * Updates the settings to the configuration specfied in the parameters
     * @param settings the new settings configuration
     * @return the new settings configuration
     */
    @RequestMapping(value = "/dhis2-settings", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Settings updateSettings(@RequestBody Settings settings) {
        settingsService.updateSettings(settings);
        return settingsService.getSettings();
    }

    /**
     * Attempts to sync the module to the DHIS2 server. If successful, updates
     * the task channel to reflect any changes to the DHIS2 schema
     * @return a boolean value indicating success or failure
     */
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
