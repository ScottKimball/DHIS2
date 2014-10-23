package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.AttributeMapper;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 9/22/14.
 */
public interface AttributeDataService extends MotechDataService <AttributeMapper> {

    @Lookup
    public AttributeMapper findByName (@LookupField(name = "name") String name);

}
