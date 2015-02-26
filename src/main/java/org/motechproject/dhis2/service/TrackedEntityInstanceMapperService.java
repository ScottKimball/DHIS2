package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;

public interface TrackedEntityInstanceMapperService {
    String getDhisId(String externalId);
    TrackedEntityInstanceMapper findByExternalId(String externalId);
    void deleteAll();
}
