package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 9/8/14.
 */
public interface DataElementDataService extends MotechDataService<DataElement> {

    @Lookup
    public DataElement findByName (@LookupField(name="name") String name);

}
