package org.motechproject.dhis2.dto;


/**
 * Created by scott on 9/4/14.
 */


public class OrgUnit {

    private String externalName;
    private String dhis2Name;
    private String dhis2Uuid;


    public OrgUnit(String externalName, String dhis2Uuid) {
        this.externalName = externalName;
        this.dhis2Uuid = dhis2Uuid;
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

    public String getDhis2Name() {
        return dhis2Name;
    }

    public void setDhis2Name(String dhis2Name) {
        this.dhis2Name = dhis2Name;
    }
}
