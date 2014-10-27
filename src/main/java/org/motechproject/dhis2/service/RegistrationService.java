package org.motechproject.dhis2.service;

import org.motechproject.dhis2.dto.TrackedEntityInstance;

/**
 * Created by scott on 10/16/14.
 */
public interface RegistrationService {

    public void send (TrackedEntityInstance trackedEntityInstance);

}
