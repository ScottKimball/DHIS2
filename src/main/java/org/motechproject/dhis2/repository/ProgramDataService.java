package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.Program;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 1/6/15.
 */
public interface ProgramDataService extends MotechDataService<Program> {

    @Lookup
    String findByUuid(@LookupField(name = "uuid") String uuid);
}
