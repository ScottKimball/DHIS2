package org.motechproject.dhis2.domain;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scott on 9/10/14.
 */

public class Enrollment {


    private ObjectMapper objectMapper;

    private Program program;
    private TrackedEntityInstance trackedEntityInstance;
    private OrgUnit orgUnit;
    private DateTime date;

    public Enrollment(Program program, TrackedEntityInstance trackedEntityInstance, OrgUnit orgUnit , DateTime date) {
        this.program = program;
        this.trackedEntityInstance = trackedEntityInstance;
        this.orgUnit = orgUnit;
        this.date = date;

        this.objectMapper = new ObjectMapper();
    }

    public String trackedEntityToJson () {
        ObjectNode root = objectMapper.createObjectNode();
        root.put("orgUnit", orgUnit.getUUID());
        root.put("trackedEntity", trackedEntityInstance.getTrackedEntityType().getUUID());

        ArrayNode attributes = objectMapper.createArrayNode();

        ArrayNode nodeList = objectMapper.createArrayNode();

        for (Attribute attribute : trackedEntityInstance.getAttributes()) {
            ObjectNode node = objectMapper.createObjectNode();
            node.put("attribute", attribute.getUuid());
            node.put("value", attribute.getValue());
            nodeList.add(node);

        }
        root.putArray("attributes").addAll(nodeList);

        try {
            String result = objectMapper.writeValueAsString(root);
            return result;
        } catch (Exception e) {

        }
        return null;
    }

    public String enrollmentToJson () {


        ObjectNode root = objectMapper.createObjectNode();
        root.put("program",program.getUUID());
        root.put("trackedEntityInstance",trackedEntityInstance.getUUID());
        root.put("dateOfEnrollment",date.toString("MM-dd-yyyy"));
        root.put("dateOfIncident",date.toString("MM-dd-yyyy"));

        try {
            String result = objectMapper.writeValueAsString(root);
            return result;
        } catch (Exception e) {

        }
        return null;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public TrackedEntityInstance getTrackedEntityInstance() {
        return trackedEntityInstance;
    }

    public void setTrackedEntityInstance(TrackedEntityInstance trackedEntityInstance) {
        this.trackedEntityInstance = trackedEntityInstance;
    }

    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }


    public static void main (String [] args) {

        List<Attribute> testList = new ArrayList<Attribute>();
        testList.add(new Attribute("testName", "testUUID", "testValue"));
        testList.add(new Attribute("testname2", "testuuid2","testvalue2"));
        List<Attribute> entityList = new ArrayList<Attribute>();
        entityList.add(new Attribute("test","test","test"));
        TrackedEntity entity = new TrackedEntity("Person");
        entity.setUUID("trackedEntityUUID");

        Program testProgram = new Program(null,null,null,null);

        TrackedEntityInstance instance = new TrackedEntityInstance("id");
        instance.setAttributes(testList);
        instance.setTrackedEntityType(entity);

        OrgUnit orgUnit = new OrgUnit("commcarename" ,null, "uuid");
        DateTime date = new DateTime();
        date = DateTime.now();

        Enrollment testEnrollment = new Enrollment(testProgram,instance,orgUnit, date);

        System.out.println(testEnrollment.trackedEntityToJson());

        instance.setUUID("testTrackeEntityUUID");

        System.out.println(testEnrollment.enrollmentToJson());
    }
}

