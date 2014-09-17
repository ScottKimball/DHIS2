package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

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
    String UUID;

    @Field
    TrackedEntity trackedEntityType;

    public Program(String commcareCaseType, String dhis2Name, String UUID , TrackedEntity trackedEntityInstance) {
        this.commcareCaseType = commcareCaseType;
        this.dhis2Name = dhis2Name;
        this.UUID = UUID;
        this.trackedEntityType = trackedEntityInstance;
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

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public TrackedEntity getTrackedEntityType() {
        return trackedEntityType;
    }

    public void setTrackedEntityType(TrackedEntity trackedEntityType) {
        this.trackedEntityType = trackedEntityType;
    }
}
