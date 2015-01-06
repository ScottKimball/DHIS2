package org.motechproject.dhis2.dto.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.motechproject.dhis2.dto.AttributeDto;
import org.motechproject.dhis2.dto.Dto;

import java.util.List;

/**
 * Created by scott on 9/10/14.
 */

public class TrackedEntityInstanceDto implements Dto {

    private String externalId;
    private String trackedEntityType;
    private String dhis2Uuid;
    private List<AttributeDto> attributeDtos;
    private String orgUnit;

    public TrackedEntityInstanceDto() {};



    public TrackedEntityInstanceDto(String externalId, String trackedEntityType, List<AttributeDto> attributeDtos, String orgUnit) {
        this.externalId = externalId;
        this.trackedEntityType = trackedEntityType;
        this.attributeDtos = attributeDtos;
        this.orgUnit = orgUnit;
    }

    public TrackedEntityInstanceDto(String externalId) {
        this.externalId = externalId;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getTrackedEntityType() {
        return trackedEntityType;
    }

    public void setTrackedEntityType(String trackedEntityType) {
        this.trackedEntityType = trackedEntityType;
    }

    public String getDhis2Uuid() {
        return dhis2Uuid;
    }

    public void setDhis2Uuid(String dhis2Uuid) {
        this.dhis2Uuid = dhis2Uuid;
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
        root.put("orgUnit", orgUnit);
        root.put("trackedEntity", trackedEntityType);

        ArrayNode nodeList = objectMapper.createArrayNode();

        for (AttributeDto attributeDto : attributeDtos) {


            ObjectNode node = objectMapper.createObjectNode();
            node.put("attribute", attributeDto.getDhis2Uuid());
            node.put("value", attributeDto.getValue());
            nodeList.add(node);
        }

        root.putArray("attributes").addAll(nodeList);

        try {
            String result = objectMapper.writeValueAsString(root);
            return result;
        } catch (Exception e) {
            /*TODO: handle exception*/
        }

        return null;
    }
}
