package org.motechproject.dhis2.dto;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import java.util.List;

/**
 * Created by scott on 9/10/14.
 */

public class TrackedEntityInstance {

    private String externalId;
    private TrackedEntity trackedEntityType;
    private String dhis2Uuid;
    private List<Attribute> attributes;
    private OrgUnit orgUnit;

    public TrackedEntityInstance() {};

    public TrackedEntityInstance(String externalId, TrackedEntity trackedEntityType, List<Attribute> attributes) {
        this.externalId = externalId;
        this.trackedEntityType = trackedEntityType;
        this.attributes = attributes;
    }

    public TrackedEntityInstance(String externalId, TrackedEntity trackedEntityType, String dhis2Uuid, List<Attribute> attributes) {
        this.externalId = externalId;
        this.trackedEntityType = trackedEntityType;
        this.dhis2Uuid = dhis2Uuid;
        this.attributes = attributes;
    }

    public TrackedEntityInstance(String externalId, TrackedEntity trackedEntityType, String dhis2Uuid, List<Attribute> attributes, OrgUnit orgUnit) {
        this.externalId = externalId;
        this.trackedEntityType = trackedEntityType;
        this.dhis2Uuid = dhis2Uuid;
        this.attributes = attributes;
        this.orgUnit = orgUnit;
    }

    public TrackedEntityInstance(String externalId) {
        this.externalId = externalId;
    }

    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public TrackedEntity getTrackedEntityType() {
        return trackedEntityType;
    }

    public void setTrackedEntityType(TrackedEntity trackedEntityType) {
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
        root.put("orgUnit", orgUnit.getDhis2Uuid());
        root.put("trackedEntity", trackedEntityType.getDhis2Uuid());

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

        }

        return null;
    }
}
