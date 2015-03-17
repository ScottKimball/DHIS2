package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.TrackedEntity;
import org.motechproject.dhis2.rest.domain.TrackedEntityDto;

import java.util.List;

/**
 * Manages CRUD operations for a {@link org.motechproject.dhis2.domain.TrackedEntity}
 */
public interface TrackedEntityService {
    List<TrackedEntity> findAll();
    TrackedEntity findById(String id);
    TrackedEntity createFromDetails(TrackedEntityDto details);
    void update(TrackedEntity trackedEntity);
    void delete(TrackedEntity trackedEntity);
    void deleteAll();
}
