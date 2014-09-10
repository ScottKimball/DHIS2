package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

/**
 * Created by scott on 9/4/14.
 */

@Entity
public class OrgUnit {

    @Field
    private String commcareName;

    @Field
    private String dhis2Name;

    @Field
    private String UUID;


    public OrgUnit(String commcareName, String dhis2Name, String UUID) {
        this.commcareName = commcareName;
        this.dhis2Name = dhis2Name;
        this.UUID = UUID;
    }

    public String getCommcareName() {
        return commcareName;
    }

    public void setCommcareName(String commcareName) {
        this.commcareName = commcareName;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getDhis2Name() {
        return dhis2Name;
    }

    public void setDhis2Name(String dhis2Name) {
        this.dhis2Name = dhis2Name;
    }
}
