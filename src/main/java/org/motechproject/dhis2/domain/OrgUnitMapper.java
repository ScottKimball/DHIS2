package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

/**
 * Created by scott on 10/22/14.
 */

@Entity
public class OrgUnitMapper {

    @Field
    private String name;

    @Field
    private String dhis2Uuid;

    public OrgUnitMapper(String name, String dhis2Uuid) {
        this.name = name;
        this.dhis2Uuid = dhis2Uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDhis2Uuid() {
        return dhis2Uuid;
    }

    public void setDhis2Uuid(String dhis2Uuid) {
        this.dhis2Uuid = dhis2Uuid;
    }
}
