package org.motechproject.dhis2.dto.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.motechproject.dhis2.dto.Dto;

import java.io.IOException;
import java.util.List;

/**
 * Created by scott on 9/10/14.
 */

public class StageDto implements Dto {

    private String externalName;
    private String dhis2Name;
    private String dhis2Uuid;
    private String program;
    private List<AttributeDto> dataElementDtos;
    private String date;
    private String trackedEntityInstance;
    private String orgUnit;
    private boolean registration;


    public StageDto(String program, String orgUnit, String date, String dhis2Uuid, String trackedEntityInstance, List<AttributeDto> dataElementDtos) {
        this.program = program;
        this.orgUnit = orgUnit;
        this.date = date;
        this.dhis2Uuid = dhis2Uuid;
        this.trackedEntityInstance = trackedEntityInstance;
        this.dataElementDtos = dataElementDtos;
    }

    public StageDto() {
    }

    public boolean hasRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
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

    public List<AttributeDto> getDataElementDtos() {
        return dataElementDtos;
    }

    public void setDataElementDtos(List<AttributeDto> dataElementDtos) {
        this.dataElementDtos = dataElementDtos;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }


    public String toJson() throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        root.put("program", getProgram());
        root.put("orgUnit", getOrgUnit());
        root.put("eventDate", getDate());
        root.put("programStage", getDhis2Uuid());

        if (registration) {
            root.put("trackedEntityInstance", getTrackedEntityInstance());
        }

        ArrayNode nodeList = objectMapper.createArrayNode();

        for (AttributeDto attributeDto : dataElementDtos) {


            ObjectNode node = objectMapper.createObjectNode();
            node.put("dataElement", attributeDto.getDhis2Uuid());
            node.put("value", attributeDto.getValue());
            nodeList.add(node);
        }

        root.putArray("dataValues").addAll(nodeList);


        return objectMapper.writeValueAsString(root);
    }

}
