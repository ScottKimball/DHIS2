package org.motechproject.dhis2.repository.Mapper;

import org.motechproject.dhis2.domain.Mapper.OrgUnitMapper;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by scott on 10/16/14.
 */
public interface OrgUnitMapperDataService extends MotechDataService<OrgUnitMapper> {

    @Lookup
    public OrgUnitMapper findByExternalName (@LookupField(name = "externalName") String externalName);

}
