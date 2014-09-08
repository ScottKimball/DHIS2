package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;

/**
 * Created by scott on 8/29/14.
 */
@Entity
public class DataValue {

    private String timePeriod;
    private OrgUnit orgUnit;
    private DataElement dataElement;
    private String value;
    private String dataSet;

    // TODO : add data element name and data element value

    public DataValue(String timePeriod, OrgUnit orgUnit, DataElement dataElement, String value , String dataSet) {
        this.timePeriod = timePeriod;
        this.orgUnit = orgUnit;
        this.dataElement = dataElement;
        this.value = value;
        this.dataSet = dataSet;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    public DataElement getDatElement() {
        return dataElement;
    }

    public void setDatElement(DataElement datElement) {
        this.dataElement = datElement;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDataSet() {
        return dataSet;
    }

    public void setDataSet(String dataSet) {
        this.dataSet = dataSet;
    }

    public String getDataElementUUID () {
        return dataElement.getUUID();
    }

    public String getOrgUnitUUID () {
        return orgUnit.getUUID();
    }

    @Override
    public String toString() {
        return "dataElement : " + dataElement.getName() +
                "\n timePeriod : " + timePeriod +
                "\n orgUnit : " + orgUnit.getName() +
                "\n value : " + value +
                "\n dataSet : " + dataSet;
    }
}



