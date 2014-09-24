package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

/**
 * Created by scott on 9/18/14.
 */

@Entity
public class Attribute {

    @Field
    private String name;

    @Field
    private String uuid;

    @Field
    private String value;

    public Attribute(String name, String uuid, String value) {
        this.uuid = uuid;
        this.value = value;
        this.name =name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
