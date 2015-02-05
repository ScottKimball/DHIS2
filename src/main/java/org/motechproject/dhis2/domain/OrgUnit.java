package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;


/**
 * Created by scott on 1/8/15.
 */

@Entity
public class OrgUnit {
    @Field
    private String uuid;

    @Field
    private String name;

    public OrgUnit() {}

    public OrgUnit(String name, String uuid) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
