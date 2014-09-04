package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

/**
 * Created by scott on 9/4/14.
 */

@Entity
public class OrgUnit {

    @Field
    private String name;

    @Field
    private String UUID;


    public OrgUnit(String name, String UUID) {
        this.name = name;
        this.UUID = UUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
