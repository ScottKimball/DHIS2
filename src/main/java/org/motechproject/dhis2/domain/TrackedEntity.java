package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

import java.util.List;

/**
 * Created by scott on 9/17/14.
 */
@Entity
public class TrackedEntity {

    @Field
    String type;

    @Field
    String UUID;

    @Field
    List<Attribute> requiredAttributes;

    public TrackedEntity (String type) {
        this.type = type;
    }


    public TrackedEntity(String type, String UUID, List<Attribute> requiredAttributes) {
        this.type = type;
        this.UUID = UUID;
        this.requiredAttributes = requiredAttributes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public List<Attribute> getRequiredAttributes() {
        return requiredAttributes;
    }

    public void setRequiredAttributes(List<Attribute> requiredAttributes) {
        this.requiredAttributes = requiredAttributes;
    }
}
