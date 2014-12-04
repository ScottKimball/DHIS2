package org.motechproject.dhis2.dto.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.motechproject.dhis2.dto.Attribute;
import org.motechproject.dhis2.dto.Dto;

import java.util.List;

/**
 * Created by scott on 9/10/14.
 */

public class TrackedEntityInstance implements Dto {

    private String externalId;
    private String trackedEntityType;
    private String dhis2Uuid;
    private List<Attribute> attributes;
    private String orgUnit;

    public TrackedEntityInstance() {};



    public TrackedEntityInstance(String externalId, String trackedEntityType, List<Attribute> attributes, String orgUnit) {
        this.externalId = externalId;
        this.trackedEntityType = trackedEntityType;
        this.attributes = attributes;
        this.orgUnit = orgUnit;
    }

    public TrackedEntityInstance(String externalId) {
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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode root = objectMapper.createObjectNode();
        root.put("orgUnit", orgUnit);
        root.put("trackedEntity", trackedEntityType);

        ArrayNode nodeList = objectMapper.createArrayNode();

        for (Attribute attribute : attributes) {


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
            /*TODO: handle exception*/
        }

        return null;
    }
}
