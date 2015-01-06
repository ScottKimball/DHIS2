package org.motechproject.dhis2.repository.Mapper;

import org.motechproject.dhis2.domain.Mapper.ProgramMapper;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 9/10/14.
 */
public interface ProgramMapperDataService extends MotechDataService<ProgramMapper> {

    @Lookup
    public ProgramMapper findByExternalName(@LookupField(name = "externalName") String externalName);

    @Lookup
    public ProgramMapper findByDhis2Name(@LookupField(name = "dhis2Name") String dhis2Name);
}
