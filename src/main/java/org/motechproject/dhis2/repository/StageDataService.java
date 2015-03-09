package org.motechproject.dhis2.repository;

import org.motechproject.dhis2.domain.Stage;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

public interface StageDataService extends MotechDataService<Stage> {

    @Lookup
    Stage findByUuid(@LookupField(name = "uuid") String uuid);
}
