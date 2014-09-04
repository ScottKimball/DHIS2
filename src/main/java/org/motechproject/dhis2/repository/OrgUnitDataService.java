package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 9/4/14.
 */
public interface OrgUnitDataService extends MotechDataService<OrgUnit> {

    @Lookup
    public OrgUnit findByOrgUnitName (@LookupField (name="name") String name);
}
