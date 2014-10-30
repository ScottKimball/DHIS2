package org.motechproject.dhis2.dto;


import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by scott on 9/10/14.
 */

public class Program {

    String externalProgramName;
    String dhis2Name;
    String dhis2Uuid;
    TrackedEntity trackedEntityType;
    List<Attribute> requiredAttributes;

    public Program(String externalProgramName, String dhis2Name, String dhis2Uuid, TrackedEntity trackedEntityInstance ,
                   List<Attribute> requiredAttributes) {
        this.externalProgramName = externalProgramName;
        this.dhis2Name = dhis2Name;
        this.dhis2Uuid = dhis2Uuid;
        this.trackedEntityType = trackedEntityInstance;
        this.requiredAttributes = requiredAttributes;
    }

    public List<Attribute> getRequiredAttributes() {
        return requiredAttributes;
    }


    public void setRequiredAttributes(List<Attribute> requiredAttributes) {
        this.requiredAttributes = requiredAttributes;
    }

    public String getExternalProgramName() {
        return externalProgramName;
    }

    public void setExternalProgramName(String externalProgramName) {
        this.externalProgramName = externalProgramName;
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

    public TrackedEntity getTrackedEntityType() {
        return trackedEntityType;
    }

    public void setTrackedEntityType(TrackedEntity trackedEntityType) {
        this.trackedEntityType = trackedEntityType;
    }


}
