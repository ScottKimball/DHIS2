package org.motechproject.dhis2.service;

import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;

/**
 * Created by scott on 10/16/14.
 */
public interface RegistrationService {

    public void send (TrackedEntityInstanceDto trackedEntityInstanceDto);

}
