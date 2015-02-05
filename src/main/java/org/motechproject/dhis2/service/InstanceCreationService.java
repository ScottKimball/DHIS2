package org.motechproject.dhis2.service;

import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;

public interface InstanceCreationService {

    void send(TrackedEntityInstanceDto trackedEntityInstanceDto, String path);

}
