package org.motechproject.dhis2.domain;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

import java.util.Map;

/**
 * Created by scott on 9/10/14.
 */

@Entity
public class Stage {

    @Field
    String commcareName;

    @Field
    String dhis2Name;

    @Field
    String dhis2Uuid;

    @Field
    Program program;

    @Field
    Map<String , String> attributes;

    @Field
    String date;

    @Field
    TrackedEntityInstance trackedEntityInstance;

    @Field
    OrgUnit orgUnit;

    private ObjectMapper objectMapper;



    public Stage(String commcareName, String dhis2Name, String dhis2Uuid, Program program,
                 Map<String, String> attributes, String date,
                 TrackedEntityInstance trackedEntityInstance,
                 OrgUnit orgUnit) {
        this.commcareName = commcareName;
        this.dhis2Name = dhis2Name;
        this.dhis2Uuid = dhis2Uuid;
        this.program = program;
        this.attributes = attributes;
        this.date = date;
        this.trackedEntityInstance = trackedEntityInstance;
        this.orgUnit = orgUnit;
        objectMapper = new ObjectMapper();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TrackedEntityInstance getTrackedEntityInstance() {
        return trackedEntityInstance;
    }

    public void setTrackedEntityInstance(TrackedEntityInstance trackedEntityInstance) {
        this.trackedEntityInstance = trackedEntityInstance;
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

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
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
        ObjectNode root = objectMapper.createObjectNode();
        root.put("program", getProgram().getDhis2Uuid());
        root.put("orgUnit", getOrgUnit().getDhis2Uuid());
        root.put("eventDate",getDate());
        root.put("programStage",getDhis2Uuid());
        root.put("trackedEntityInstance",getTrackedEntityInstance().getDhis2Uuid());
        root.put("status","COMPLETED");


        try {
            String result = objectMapper.writeValueAsString(root);
            return result;
        } catch (Exception e) {

        }
        return null;

    }

    public static void main(String[] args) {
        // program orgunit trackedentityinstance

        Program program = new Program(null,null,"programUUID",null,null);
        OrgUnit orgUnit = new OrgUnit(null,null,"orgUnitUUID");
        TrackedEntityInstance instance = new TrackedEntityInstance(null,null,"trackedEntityUUID",null);
        Stage stage = new Stage("name","name","stageUUID",program,null,"2015-10-06",instance,orgUnit);

        System.out.println(stage.toJson());
    }
}
