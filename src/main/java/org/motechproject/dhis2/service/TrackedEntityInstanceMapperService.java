package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;

import java.util.List;

/**
 * Manages CRUD operations for a {@link org.motechproject.dhis2.domain.TrackedEntityInstanceMapper}
 */
public interface TrackedEntityInstanceMapperService {
    List<TrackedEntityInstanceMapper> findAll();
    TrackedEntityInstanceMapper findByExternalId(String externalId);
    TrackedEntityInstanceMapper create(String externalId, String dhisId);
    String mapFromExternalId(String externalId);
    void update(TrackedEntityInstanceMapper mapper);
    void delete(TrackedEntityInstanceMapper mapper);
    void deleteAll();
}
