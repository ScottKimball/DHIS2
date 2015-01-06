package org.motechproject.dhis2.dto;


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
        this.name =name;
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
}
