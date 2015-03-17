package org.motechproject.dhis2.service;


import org.motechproject.dhis2.domain.Settings;

/**
 * Retrieves and updates settings for the module
 * @see org.motechproject.dhis2.domain.Settings
 */
public interface SettingsService {
    Settings getSettings();
    void updateSettings(Settings settings);
}
