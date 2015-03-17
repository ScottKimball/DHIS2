package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.rest.domain.OrganisationUnitDto;

import java.util.List;


/**
 * Manages CRUD operations for a {@link org.motechproject.dhis2.domain.OrgUnit}
 */
public interface OrgUnitService {
    List<OrgUnit> findAll();
    OrgUnit findById(String id);
    OrgUnit findByName(String name);
    OrgUnit createFromDetails(OrganisationUnitDto details);
    void update(OrgUnit orgUnit);
    void delete(OrgUnit orgUnit);
    void deleteAll();
}
