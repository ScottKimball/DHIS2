package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.TrackedEntity;
import org.motechproject.dhis2.rest.domain.TrackedEntityDto;

public interface TrackedEntityService {
    TrackedEntity findById(String id);
    TrackedEntity createFromDetails(TrackedEntityDto details);
    void deleteAll();
}
