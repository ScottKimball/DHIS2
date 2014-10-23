package org.motechproject.dhis2.dto;


/**
 * Created by scott on 9/4/14.
 */


public class OrgUnit {

    private String commcareName;
    private String dhis2Name;
    private String dhis2Uuid;


    public OrgUnit(String commcareName, String dhis2Name, String dhis2Uuid) {
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

    public String getDhis2Uuid() {
        return dhis2Uuid;
    }

    public void setDhis2Uuid(String dhis2Uuid) {
        this.dhis2Uuid = dhis2Uuid;
    }

    public String getDhis2Name() {
        return dhis2Name;
    }

    public void setDhis2Name(String dhis2Name) {
        this.dhis2Name = dhis2Name;
    }
}
