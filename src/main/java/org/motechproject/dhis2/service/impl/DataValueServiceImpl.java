package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.dhis2.domain.DataValue;
import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.repository.DataElementDataService;
import org.motechproject.dhis2.repository.DataValueDataService;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.service.DataValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by scott on 9/8/14.
 */

@Service
public class DataValueServiceImpl implements DataValueService{

    private DataElementDataService dataElementDataService;
    private OrgUnitDataService orgUnitDataService;
    private DataValueDataService dataValueDataService;

    private static final String DATA_SET = "yDNKJcKtXJ6";    // UUID for data set hardcoded in from "Example Data Set"

    @Autowired
    public DataValueServiceImpl(DataElementDataService dataElementDataService , OrgUnitDataService orgUnitDataService , DataValueDataService dataValueDataService) {
        this.dataElementDataService = dataElementDataService;
        this.orgUnitDataService = orgUnitDataService;
        this.dataValueDataService = dataValueDataService;
    }

    @Override
    public DataValue create(String name, String value, String orgUnit, String timePeriod) {

        DataElement dataValueDataElement = dataElementDataService.findByName(name);
        OrgUnit dataValueOrgUnit = orgUnitDataService.findByOrgUnitName(orgUnit);

        DataValue dataValue = new DataValue(timePeriod,dataValueOrgUnit, dataValueDataElement, value, DATA_SET);

        dataValueDataService.create(dataValue);

        return dataValue;

    }
}
