package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.dhis2.repository.DataElementDataService;
import org.motechproject.dhis2.rest.domain.DataElementDto;
import org.motechproject.dhis2.service.DataElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dataElementService")
public class DataElementServiceImpl implements DataElementService {
    @Autowired
    private DataElementDataService dataElementDataService;

    @Override
    public DataElement findById(String id) {
        return dataElementDataService.findByUuid(id);
    }

    @Override
    public DataElement createFromDetails(DataElementDto details) {
        DataElement dataElement = new DataElement();
        dataElement.setUuid(details.getId());
        dataElement.setName(details.getName());
        return dataElementDataService.create(dataElement);
    }

    @Override
    public void deleteAll() {
        dataElementDataService.deleteAll();
    }
}
