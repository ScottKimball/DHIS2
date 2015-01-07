package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 9/17/14.
 */
public interface TrackedEntityInstanceDataService extends MotechDataService<TrackedEntityInstanceMapper> {

    @Lookup
    public TrackedEntityInstanceMapper findByExternalName(@LookupField(name = "externalName") String externalName);

}
