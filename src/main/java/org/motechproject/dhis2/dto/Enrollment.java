package org.motechproject.dhis2.dto;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by scott on 9/10/14.
 */

public class Enrollment {

    private Program program;
    private TrackedEntityInstance trackedEntityInstance;
    private OrgUnit orgUnit;
    private DateTime date;

    public Enrollment(Program program, TrackedEntityInstance trackedEntityInstance, OrgUnit orgUnit , DateTime date) {
        this.program = program;
        this.trackedEntityInstance = trackedEntityInstance;
        this.orgUnit = orgUnit;
        this.date = date;

    }

    public String trackedEntityToJson () {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        root.put("orgUnit", orgUnit.getDhis2Uuid());
        root.put("trackedEntity", trackedEntityInstance.getTrackedEntityType().getDhis2Uuid());

        ArrayNode nodeList = objectMapper.createArrayNode();


        for (Attribute attribute : trackedEntityInstance.getAttributes()) {

            /* Have to filter out National identifier as it has to be "unique" upon enrollment in program
            This may pose a problem further on.
             */
            if (attribute.getName().equals("nationalIdentifier"))
                continue;

            ObjectNode node = objectMapper.createObjectNode();
            node.put("attribute", attribute.getDhis2Uuid());
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
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Attribute> trackedEntityAttributeMap = new HashMap<String, Attribute>();
        List<Attribute> trackedEntityAttributeList = trackedEntityInstance.getAttributes();

        for (Attribute attribute : trackedEntityAttributeList) {
            trackedEntityAttributeMap.put(attribute.getName(),attribute);
        }

        ObjectNode root = objectMapper.createObjectNode();
        root.put("program",program.getDhis2Uuid());
        root.put("trackedEntityInstance",trackedEntityInstance.getDhis2Uuid());

        ArrayNode nodeList = objectMapper.createArrayNode();

        List<Attribute> programRequiredAttributes = program.getRequiredAttributes();
        for (Attribute attribute : programRequiredAttributes) {
            ObjectNode node = objectMapper.createObjectNode();
            node.put("attribute", attribute.getDhis2Uuid());

            Attribute trackedEntityAttribute = trackedEntityAttributeMap.get(attribute.getName());
            node.put("value", trackedEntityAttribute.getValue());
            nodeList.add(node);

        }
        root.putArray("attributes").addAll(nodeList);
       // root.put("dateOfEnrollment",date.toString("MM-dd-yyyy"));
       // root.put("dateOfIncident",date.toString("MM-dd-yyyy"));

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
        Attribute testAttribute = new Attribute("testName", "testUUID", "testValue");
        testList.add(testAttribute);
        testList.add(new Attribute("testname2", "testuuid2","testvalue2"));
        List<Attribute> entityList = new ArrayList<Attribute>();
        entityList.add(new Attribute("test","test","test"));
        TrackedEntity entity = new TrackedEntity("Person");
        entity.setDhis2Uuid("trackedEntityUUID");

        List<Attribute> programRequiredAttributes = new ArrayList<Attribute>();
        programRequiredAttributes.add(testAttribute);
        Program testProgram = new Program(null,null,null,null,programRequiredAttributes);

        TrackedEntityInstance instance = new TrackedEntityInstance("id");
        instance.setAttributes(testList);
        instance.setTrackedEntityType(entity);

        OrgUnit orgUnit = new OrgUnit("commcarename" ,null, "uuid");
        DateTime date = new DateTime();
        date = DateTime.now();

        Enrollment testEnrollment = new Enrollment(testProgram,instance,orgUnit, date);

        System.out.println(testEnrollment.trackedEntityToJson());

        instance.setDhis2Uuid("testTrackeEntityUUID");

        System.out.println(testEnrollment.enrollmentToJson());
    }
}

