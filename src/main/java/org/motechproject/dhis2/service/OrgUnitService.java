package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.rest.domain.OrganisationUnitDto;

public interface OrgUnitService {
    OrgUnit findById(String id);
    OrgUnit createFromDetails(OrganisationUnitDto details);
    void deleteAll();
}
