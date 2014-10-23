package org.motechproject.dhis2.dto;

import java.util.List;

/**
 * Created by scott on 9/17/14.
 */

public class TrackedEntity {

    private String type;
    private String dhis2Uuid;
    private List<Attribute> requiredAttributes;

    public TrackedEntity (String type) {
        this.type = type;
    }


    public TrackedEntity(String type, String dhis2Uuid, List<Attribute> requiredAttributes) {
        this.type = type;
        this.dhis2Uuid = dhis2Uuid;
        this.requiredAttributes = requiredAttributes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDhis2Uuid() {
        return dhis2Uuid;
    }

    public void setDhis2Uuid(String dhis2Uuid) {
        this.dhis2Uuid = dhis2Uuid;
    }

    public List<Attribute> getRequiredAttributes() {
        return requiredAttributes;
    }

    public void setRequiredAttributes(List<Attribute> requiredAttributes) {
        this.requiredAttributes = requiredAttributes;
    }


}
