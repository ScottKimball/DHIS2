package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.service.OrgUnitService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by scott on 9/4/14.
 */
public class OrgUnitServiceImpl implements OrgUnitService{

    private OrgUnitDataService orgUnitDataService;

    @Autowired
    public OrgUnitServiceImpl(OrgUnitDataService orgUnitDataService) {
        this.orgUnitDataService = orgUnitDataService;
    }

    @Override
    public String getUUIDByOrgUnitName(String orgUnitName) {
        OrgUnit orgUnit = orgUnitDataService.findByOrgUnitName(orgUnitName);
        return orgUnit.getUUID();
    }
}
