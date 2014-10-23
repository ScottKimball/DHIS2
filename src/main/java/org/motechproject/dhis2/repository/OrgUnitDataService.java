package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.OrgUnitMapper;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 10/16/14.
 */
public interface OrgUnitDataService extends MotechDataService<OrgUnitMapper> {

    @Lookup
    public OrgUnitMapper findByName (@LookupField(name = "name") String name);

}
