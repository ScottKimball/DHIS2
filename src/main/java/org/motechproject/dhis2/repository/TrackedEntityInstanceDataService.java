package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * MDS data service for {@link org.motechproject.dhis2.domain.TrackedEntityInstanceMapper}
 */
public interface TrackedEntityInstanceDataService extends MotechDataService<TrackedEntityInstanceMapper> {
    @Lookup
    TrackedEntityInstanceMapper findByExternalName(@LookupField(name = "externalName") String externalName);
}
