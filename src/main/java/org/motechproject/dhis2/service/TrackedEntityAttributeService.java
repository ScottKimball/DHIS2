package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.TrackedEntityAttribute;
import org.motechproject.dhis2.rest.domain.TrackedEntityAttributeDto;

import java.util.List;

public interface TrackedEntityAttributeService {
    List<TrackedEntityAttribute> findAll();
    TrackedEntityAttribute findById(String id);
    TrackedEntityAttribute createFromDetails(TrackedEntityAttributeDto details);
    void deleteAll();
}
