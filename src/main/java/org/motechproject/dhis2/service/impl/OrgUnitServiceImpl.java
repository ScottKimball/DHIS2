package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.rest.domain.OrganisationUnitDto;
import org.motechproject.dhis2.service.OrgUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orgUnitService")
public class OrgUnitServiceImpl implements OrgUnitService {
    @Autowired
    private OrgUnitDataService orgUnitDataService;

    @Override
    public OrgUnit findById(String id) {
        return orgUnitDataService.findByUuid(id);
    }

    @Override
    public OrgUnit findByName(String name) {
        return orgUnitDataService.findByName(name);
    }

    @Override
    public OrgUnit createFromDetails(OrganisationUnitDto details) {
        OrgUnit orgUnit = new OrgUnit();
        orgUnit.setUuid(details.getId());
        orgUnit.setName(details.getName());
        return orgUnitDataService.create(orgUnit);
    }

    @Override
    public void deleteAll() {
        orgUnitDataService.deleteAll();
    }
}
