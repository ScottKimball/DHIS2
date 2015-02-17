package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.domain.TrackedEntity;
import org.motechproject.dhis2.repository.TrackedEntityDataService;
import org.motechproject.dhis2.rest.domain.TrackedEntityDto;
import org.motechproject.dhis2.service.TrackedEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("trackedEntityService")
public class TrackedEntityServiceImpl implements TrackedEntityService {
    @Autowired
    private TrackedEntityDataService trackedEntityDataService;

    @Override
    public TrackedEntity findById(String id) {
        return trackedEntityDataService.findByUuid(id);
    }

    @Override
    public TrackedEntity createFromDetails(TrackedEntityDto details) {
        TrackedEntity trackedEntity = new TrackedEntity();
        trackedEntity.setUuid(details.getId());
        trackedEntity.setName(details.getName());
        return trackedEntityDataService.create(trackedEntity);
    }

    @Override
    public void deleteAll() {
        trackedEntityDataService.deleteAll();
    }
}
