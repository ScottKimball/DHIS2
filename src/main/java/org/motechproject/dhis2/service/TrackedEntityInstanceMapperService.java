package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;

public interface TrackedEntityInstanceMapperService {
    TrackedEntityInstanceMapper create(String externalId, String dhisId);
    String mapFromExternalId(String externalId);
    TrackedEntityInstanceMapper findByExternalId(String externalId);
    void deleteAll();
}
