package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.dhis2.service.TrackedEntityInstanceMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("trackedEntityInstanceMapperService")
public class TrackedEntityInstanceMapperServiceImpl implements TrackedEntityInstanceMapperService {
    @Autowired
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;

    @Override
    public String getDhisId(String externalId) {
        TrackedEntityInstanceMapper mapper = trackedEntityInstanceDataService.findByExternalName(externalId);
        return mapper.getDhis2Uuid();
    }
}
