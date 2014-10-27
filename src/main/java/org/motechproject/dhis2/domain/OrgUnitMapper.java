package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

/**
 * Created by scott on 10/22/14.
 */

@Entity
public class OrgUnitMapper {

    @Field
    private String externalName;

    @Field
    private String dhis2Name;

    @Field
    private String dhis2Uuid;

    public OrgUnitMapper(String externalName, String dhis2Name, String dhis2Uuid) {
        this.externalName = externalName;
        this.dhis2Name = dhis2Name;
        this.dhis2Uuid = dhis2Uuid;
    }

    public String getDhis2Name() {
        return dhis2Name;
    }

    public void setDhis2Name(String dhis2Name) {
        this.dhis2Name = dhis2Name;
    }

    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
    }

    public String getDhis2Uuid() {
        return dhis2Uuid;
    }

    public void setDhis2Uuid(String dhis2Uuid) {
        this.dhis2Uuid = dhis2Uuid;
    }
}
