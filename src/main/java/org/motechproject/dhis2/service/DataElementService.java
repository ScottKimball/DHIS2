package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.dhis2.rest.domain.DataElementDto;

public interface DataElementService {
    DataElement findById(String id);
    DataElement createFromDetails(DataElementDto details);
    void deleteAll();
}
