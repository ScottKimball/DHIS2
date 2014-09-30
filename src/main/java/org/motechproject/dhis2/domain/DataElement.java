package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

/**
 * Created by scott on 9/4/14.
 */

@Entity
public class DataElement {

    @Field
    private String commcareName;

    @Field String dhis2Name;

    @Field
    private String dhis2Uuid;

    public DataElement(String commcareName,String dhis2Name,  String dhis2Uuid) {
        this.commcareName = commcareName;
        this.dhis2Name = dhis2Name;
        this.dhis2Uuid = dhis2Uuid;

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

    public String getDhis2Uuid() {
        return dhis2Uuid;
    }

    public void setDhis2Uuid(String dhis2Uuid) {
        this.dhis2Uuid = dhis2Uuid;
    }
}
