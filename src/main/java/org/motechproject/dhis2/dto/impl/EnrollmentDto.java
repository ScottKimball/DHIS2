package org.motechproject.dhis2.dto.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.motechproject.dhis2.dto.Dto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by scott on 9/10/14.
 */

public class EnrollmentDto implements Dto {

    private String program;
    private String trackedEntityInstance;
    private String date;
    private List<AttributeDto> attributeDtos;

    private Logger logger = LoggerFactory.getLogger(EnrollmentDto.class);


    public EnrollmentDto(String program, String trackedEntityInstance, String date, List<AttributeDto> attributeDtos) {
        this.program = program;
        this.trackedEntityInstance = trackedEntityInstance;
        this.date = date;
        this.attributeDtos = attributeDtos;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<AttributeDto> getAttributeDtos() {
        return attributeDtos;
    }

    public void setAttributeDtos(List<AttributeDto> attributeDtos) {
        this.attributeDtos = attributeDtos;
    }

    public String toJson() {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode root = objectMapper.createObjectNode();
        root.put("program", program);
        root.put("trackedEntityInstance", trackedEntityInstance);

        if (date != null) {
            root.put("dateOfEnrollment", date);
        }

        ArrayNode nodeList = objectMapper.createArrayNode();

        for (AttributeDto attributeDto : attributeDtos) {
            ObjectNode node = objectMapper.createObjectNode();
            node.put("attribute", attributeDto.getDhis2Uuid());

            node.put("value", attributeDto.getValue());
            nodeList.add(node);

        }
        root.putArray("attributes").addAll(nodeList);

        try {
            return objectMapper.writeValueAsString(root);
        } catch (Exception e) {
            logger.debug(e.toString());
        }
        return null;
    }


}

