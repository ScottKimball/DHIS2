package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

import java.util.List;

/**
 * Created by scott on 9/10/14.
 */

@Entity
public class TrackedEntityInstance {

    @Field
    String commcareId;


    @Field
    TrackedEntity trackedEntityType;

    @Field
    String UUID;

    @Field
    List<Attribute> attributes;

    public TrackedEntityInstance() {};

    public TrackedEntityInstance(String commcareId, TrackedEntity trackedEntityType, List<Attribute> attributes) {
        this.commcareId = commcareId;
        this.trackedEntityType = trackedEntityType;
        this.attributes = attributes;
    }

    public TrackedEntityInstance(String commcareId, TrackedEntity trackedEntityType, String UUID, List<Attribute> attributes) {
        this.commcareId = commcareId;
        this.trackedEntityType = trackedEntityType;
        this.UUID = UUID;
        this.attributes = attributes;
    }

    public TrackedEntityInstance(String commcareId) {
        this.commcareId = commcareId;
    }

    public String getCommcareId() {
        return commcareId;
    }

    public void setCommcareId(String commcareId) {
        this.commcareId = commcareId;
    }

    public TrackedEntity getTrackedEntityType() {
        return trackedEntityType;
    }

    public void setTrackedEntityType(TrackedEntity trackedEntityType) {
        this.trackedEntityType = trackedEntityType;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
