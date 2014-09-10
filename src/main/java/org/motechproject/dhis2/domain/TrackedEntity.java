package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

import java.util.Map;

/**
 * Created by scott on 9/10/14.
 */

@Entity
public class TrackedEntity {

    @Field
    String commcareName;

    @Field
    String dhis2Name;

    @Field
    String UUID;

    @Field
    Map<String, String> attributes;

    public TrackedEntity(String commcareName, String dhis2Name, String UUID, Map<String, String> attributes) {
        this.commcareName = commcareName;
        this.dhis2Name = dhis2Name;
        this.UUID = UUID;
        this.attributes = attributes;
    }

    public String getCommcareName() {
        return commcareName;
    }

    public void setCommcareName(String commcareName) {
        this.commcareName = commcareName;
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

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
