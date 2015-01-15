package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.StageMapper;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 9/10/14.
 */
public interface StageMapperDataService extends MotechDataService<StageMapper> {

    @Lookup
    StageMapper findByExternalName(@LookupField(name = "externalName") String externalName);

    @Lookup
    StageMapper findByDhis2Name(@LookupField(name = "dhis2Name") String dhis2Name);


}
