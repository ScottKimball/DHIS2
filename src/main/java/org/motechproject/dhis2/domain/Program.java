package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

import java.util.List;

/**
 * Created by scott on 9/10/14.
 */

@Entity
public class Program {

    @Field
    String commcareCaseType;

    @Field
    String dhis2Name;

    @Field
    String dhis2Uuid;

    @Field
    TrackedEntity trackedEntityType;

    @Field
    List<Attribute> requiredAttributes;

    public Program(String commcareCaseType, String dhis2Name, String dhis2Uuid, TrackedEntity trackedEntityInstance ,
                   List<Attribute> requiredAttributes) {
        this.commcareCaseType = commcareCaseType;
        this.dhis2Name = dhis2Name;
        this.dhis2Uuid = dhis2Uuid;
        this.trackedEntityType = trackedEntityInstance;
        this.requiredAttributes = requiredAttributes;
    }

    public List<Attribute> getRequiredAttributes() {
        return requiredAttributes;
    }

    public void setRequiredAttributes(List<Attribute> requiredAttributes) {
        this.requiredAttributes = requiredAttributes;
    }

    public String getCommcareCaseType() {
        return commcareCaseType;
    }

    public void setCommcareCaseType(String commcareCaseType) {
        this.commcareCaseType = commcareCaseType;
    }

    public String getDhis2Name() {
        return dhis2Name;
    }

    public void setDhis2Name(String dhis2Name) {
        this.dhis2Name = dhis2Name;
    }

    public String getDhis2Uuid() {
        return dhis2Uuid;
    }

    public void setDhis2Uuid(String dhis2Uuid) {
        this.dhis2Uuid = dhis2Uuid;
    }

    public TrackedEntity getTrackedEntityType() {
        return trackedEntityType;
    }

    public void setTrackedEntityType(TrackedEntity trackedEntityType) {
        this.trackedEntityType = trackedEntityType;
    }
}
