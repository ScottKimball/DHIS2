package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.dhis2.rest.domain.DataElementDto;

import java.util.List;


/**
 * Manages CRUD operations for a {@link org.motechproject.dhis2.domain.DataElement}
 */
public interface DataElementService {
    List<DataElement> findAll();
    DataElement findById(String id);
    DataElement createFromDetails(DataElementDto details);
    void update(DataElement dataElement);
    void delete(DataElement dataElement);
    void deleteAll();
}
