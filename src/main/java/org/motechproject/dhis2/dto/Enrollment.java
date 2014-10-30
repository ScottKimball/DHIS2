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

    private String program;
    private String trackedEntityInstance;
    private DateTime date;
    private List<Attribute> attributes;


    public Enrollment(String program, String trackedEntityInstance, DateTime date, List<Attribute> attributes) {
        this.program = program;
        this.trackedEntityInstance = trackedEntityInstance;
        this.date = date;
        this.attributes = attributes;
    }



    public String toJson () {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Attribute> trackedEntityAttributeMap = new HashMap<String, Attribute>();


        ObjectNode root = objectMapper.createObjectNode();
        root.put("program",program);
        root.put("trackedEntityInstance",trackedEntityInstance);

        ArrayNode nodeList = objectMapper.createArrayNode();


        for (Attribute attribute : attributes) {
            ObjectNode node = objectMapper.createObjectNode();
            node.put("attribute", attribute.getDhis2Uuid());

            Attribute trackedEntityAttribute = trackedEntityAttributeMap.get(attribute.getName());
            node.put("value", trackedEntityAttribute.getValue());
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getTrackedEntityInstance() {
        return trackedEntityInstance;
    }

    public void setTrackedEntityInstance(String trackedEntityInstance) {
        this.trackedEntityInstance = trackedEntityInstance;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public static void main (String [] args) {


    }
}

