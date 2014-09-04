package org.motechproject.dhis2.domain;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by scott on 8/29/14.
 */
public class DataValue {

    private String timePeriod;
    private String orgUnit;
    private String dataElement;

    // TODO : add data element name and data element value

    public DataValue(String timePeriod, String orgUnit, String datElement) {
        this.timePeriod = timePeriod;
        this.orgUnit = orgUnit;
        this.dataElement = datElement;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }

    public String getDatElement() {
        return dataElement;
    }

    public void setDatElement(String datElement) {
        this.dataElement = datElement;
    }
}
