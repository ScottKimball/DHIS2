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

public class StageDto implements Dto {

    private String externalName;
    private String dhis2Name;
    private String dhis2Uuid;
    private String program;
    private List<DataElementDto> dataElementDtos;
    private String date;
    private String trackedEntityInstance;
    private String orgUnit;

    private Logger logger = LoggerFactory.getLogger(StageDto.class);


    public StageDto(String program, String orgUnit, String date, String dhis2Uuid, String trackedEntityInstance, List<DataElementDto> dataElementDtos) {
        this.program = program;
        this.orgUnit = orgUnit;
        this.date = date;
        this.dhis2Uuid = dhis2Uuid;
        this.trackedEntityInstance = trackedEntityInstance;
        this.dataElementDtos = dataElementDtos;
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

    public List<DataElementDto> getDataElementDtos() {
        return dataElementDtos;
    }

    public void setDataElementDtos(List<DataElementDto> dataElementDtos) {
        this.dataElementDtos = dataElementDtos;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }


    public String toJson() {


        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        root.put("program", getProgram());
        root.put("orgUnit", getOrgUnit());
        root.put("eventDate", getDate());
        root.put("programStage", getDhis2Uuid());
        root.put("trackedEntityInstance", getTrackedEntityInstance());
        root.put("status", "COMPLETED");

        ArrayNode nodeList = objectMapper.createArrayNode();

        for (AttributeDto attributeDto : dataElementDtos) {


            ObjectNode node = objectMapper.createObjectNode();
            node.put("dataElement", attributeDto.getDhis2Uuid());
            node.put("value", attributeDto.getValue());
            nodeList.add(node);
        }

        root.putArray("dataValues").addAll(nodeList);


        try {
            return objectMapper.writeValueAsString(root);
        } catch (Exception e) {
            logger.debug(e.toString());
        }
        return null;

    }


}
