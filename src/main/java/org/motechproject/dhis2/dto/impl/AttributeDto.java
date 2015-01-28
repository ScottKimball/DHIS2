package org.motechproject.dhis2.dto.impl;


/**
 * Created by scott on 9/18/14.
 */


public class AttributeDto {

    private String name;
    private String dhis2Uuid;
    private String value;

    public AttributeDto(String name, String dhis2Uuid, String value) {
        this.dhis2Uuid = dhis2Uuid;
        this.value = value;
        this.name = name;
    }

    public String getDhis2Uuid() {
        return dhis2Uuid;
    }

    public void setDhis2Uuid(String dhis2Uuid) {
        this.dhis2Uuid = dhis2Uuid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AttributeDto)) {
            return false;
        }
        AttributeDto other = (AttributeDto) obj;
        return this.getDhis2Uuid().equals(other.getDhis2Uuid()) && this.getValue().equals(other.getValue());
    }

    @Override
    public int hashCode() {
        int result =  dhis2Uuid.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
