package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.ProgramMapper;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 9/10/14.
 */
public interface ProgramDataService extends MotechDataService<ProgramMapper> {

    @Lookup
    public ProgramMapper findByExternalName(@LookupField(name = "externalName") String externalName);
}
