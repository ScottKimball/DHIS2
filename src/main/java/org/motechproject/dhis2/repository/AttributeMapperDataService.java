package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.AttributeMapper;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 9/22/14.
 */
public interface AttributeMapperDataService extends MotechDataService<AttributeMapper> {

    @Lookup
    AttributeMapper findByExternalName(@LookupField(name = "externalName") String name);

    @Lookup
    AttributeMapper findByDhis2Name(@LookupField(name = "dhis2Name") String dhis2Name);

}
