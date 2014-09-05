package org.motechproject.dhis2.domain;

/**
 * Created by scott on 8/29/14.
 */
public class DataValue {

    private String timePeriod;
    private OrgUnit orgUnit;
    private DataElement dataElement;
    private String value;

    // TODO : add data element name and data element value

    public DataValue(String timePeriod, OrgUnit orgUnit, DataElement datElement, String value) {
        this.timePeriod = timePeriod;
        this.orgUnit = orgUnit;
        this.dataElement = datElement;
        this.value = value;
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
}

