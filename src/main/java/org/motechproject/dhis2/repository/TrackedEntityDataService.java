package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.TrackedEntityMapper;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 9/17/14.
 */
public interface TrackedEntityDataService extends MotechDataService<TrackedEntityMapper> {

    @Lookup
    public TrackedEntityMapper findByExternalName(@LookupField(name = "externalName") String externalName);
}
