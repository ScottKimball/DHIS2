package org.motechproject.dhis2.dto;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import java.util.Map;

/**
 * Created by scott on 9/10/14.
 */

public class Stage {

    private String externalName;
    private String dhis2Name;
    private String dhis2Uuid;
    private String program;
    private Map<String , String> attributes;
    private String date;
    private String trackedEntityInstance;
    private String orgUnit;


    public Stage(String program, String orgUnit, String date, String dhis2Uuid, String trackedEntityInstance) {
        this.program = program;
        this.orgUnit = orgUnit;
        this.date = date;
        this.dhis2Uuid = dhis2Uuid;
        this.trackedEntityInstance = trackedEntityInstance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrackedEntityInstance() {
        return trackedEntityInstance;
    }

    public void setTrackedEntityInstance(String trackedEntityInstance) {
        this.trackedEntityInstance = trackedEntityInstance;
    }

    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }



    public String toJson() {

  /*
  {
  "program": "ur1Edk5Oe2n",
  "orgUnit": "g8upMTyEZGZ",
  "eventDate": "2015-10-06",
  "programStage" : "ZkbAXlQUYJG",
  "trackedEntityInstance" : "cNLNq7qonVh",
  "status" : "COMPLETED"
}
   */
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        root.put("program", getProgram());
        root.put("orgUnit", getOrgUnit());
        root.put("eventDate",getDate());
        root.put("programStage",getDhis2Uuid());
        root.put("trackedEntityInstance",getTrackedEntityInstance());
        root.put("status","COMPLETED");


        try {
            String result = objectMapper.writeValueAsString(root);
            return result;
        } catch (Exception e) {

        }
        return null;

    }

    public static void main(String[] args) {

        String program = "ProgramUUID";
        String orgUnit = "OrgUnitUUID";
        String instance = "InstanceUUID";
        String date = "date";
        String stageUUID = "stageUUID";
        Stage stage = new Stage(program,orgUnit,date,stageUUID,instance);

        System.out.println(stage.toJson());
    }
}
