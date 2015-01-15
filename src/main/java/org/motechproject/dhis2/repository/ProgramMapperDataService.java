package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.ProgramMapper;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 9/10/14.
 */
public interface ProgramMapperDataService extends MotechDataService<ProgramMapper> {

    @Lookup
    ProgramMapper findByExternalName(@LookupField(name = "externalName") String externalName);

    @Lookup
    ProgramMapper findByDhis2Name(@LookupField(name = "dhis2Name") String dhis2Name);
}
