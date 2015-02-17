package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.TrackedEntityAttribute;
import org.motechproject.dhis2.rest.domain.TrackedEntityAttributeDto;

public interface TrackedEntityAttributeService {
    TrackedEntityAttribute findById(String id);
    TrackedEntityAttribute createFromDetails(TrackedEntityAttributeDto details);
    void deleteAll();
}
