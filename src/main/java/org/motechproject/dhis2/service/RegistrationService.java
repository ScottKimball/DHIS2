package org.motechproject.dhis2.service;

import org.motechproject.dhis2.dto.Dto;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstance;

/**
 * Created by scott on 10/16/14.
 */
public interface RegistrationService {

    public void send (TrackedEntityInstance trackedEntityInstance);

}
