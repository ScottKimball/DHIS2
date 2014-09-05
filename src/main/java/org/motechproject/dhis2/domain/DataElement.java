package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

/**
 * Created by scott on 9/4/14.
 */

@Entity
public class DataElement {

    @Field
    private String name;

    @Field
    private String timePeriodType;

    @Field
    private String UUID;

    public DataElement(String name, String timePeriodType, String UUID) {
        this.name = name;
        this.timePeriodType = timePeriodType;
        this.UUID = UUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimePeriodType() {
        return timePeriodType;
    }

    public void setTimePeriodType(String timePeriodType) {
        this.timePeriodType = timePeriodType;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
