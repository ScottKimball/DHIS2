package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.dhis2.service.TrackedEntityInstanceMapperService;
import org.motechproject.dhis2.service.TrackedEntityInstanceMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Implementation of {@link org.motechproject.dhis2.service.TrackedEntityInstanceMapperService}
 */
@Service("trackedEntityInstanceMapperService")
public class TrackedEntityInstanceMapperServiceImpl implements TrackedEntityInstanceMapperService {
    @Autowired
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;

    @Override
    public TrackedEntityInstanceMapper create(String externalId, String dhisId) {
        TrackedEntityInstanceMapper mapper = new TrackedEntityInstanceMapper();
        mapper.setExternalName(externalId);
        mapper.setDhis2Uuid(dhisId);
        return trackedEntityInstanceDataService.create(mapper);
    }

    @Override
    public String mapFromExternalId(String externalId) {
        TrackedEntityInstanceMapper mapper = trackedEntityInstanceDataService.findByExternalName(externalId);

        if (mapper == null) {
            throw new TrackedEntityInstanceMappingException("Failed to map for externalId: " + externalId);
        }

        return mapper.getDhis2Uuid();
    }

    @Override
    public void update(TrackedEntityInstanceMapper mapper) {
        trackedEntityInstanceDataService.update(mapper);
    }

    @Override
    public void delete(TrackedEntityInstanceMapper mapper) {
        trackedEntityInstanceDataService.delete(mapper);
    }

    @Override
    public List<TrackedEntityInstanceMapper> findAll() {
        return trackedEntityInstanceDataService.retrieveAll();
    }

    @Override
    public TrackedEntityInstanceMapper findByExternalId(String externalId) {
        return trackedEntityInstanceDataService.findByExternalName(externalId);
    }

    @Override
    public void deleteAll() {
        trackedEntityInstanceDataService.deleteAll();
    }
}
