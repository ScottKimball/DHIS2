package org.motechproject.dhis2.dto;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import java.util.List;

/**
 * Created by scott on 9/10/14.
 */

public class TrackedEntityInstance {

    private String commcareId;
    private TrackedEntity trackedEntityType;
    private String dhis2Uuid;
    private List<Attribute> attributes;
    private OrgUnit orgUnit;

    public TrackedEntityInstance() {};

    public TrackedEntityInstance(String commcareId, TrackedEntity trackedEntityType, List<Attribute> attributes) {
        this.commcareId = commcareId;
        this.trackedEntityType = trackedEntityType;
        this.attributes = attributes;
    }

    public TrackedEntityInstance(String commcareId, TrackedEntity trackedEntityType, String dhis2Uuid, List<Attribute> attributes) {
        this.commcareId = commcareId;
        this.trackedEntityType = trackedEntityType;
        this.dhis2Uuid = dhis2Uuid;
        this.attributes = attributes;
    }

    public TrackedEntityInstance(String commcareId, TrackedEntity trackedEntityType, String dhis2Uuid, List<Attribute> attributes, OrgUnit orgUnit) {
        this.commcareId = commcareId;
        this.trackedEntityType = trackedEntityType;
        this.dhis2Uuid = dhis2Uuid;
        this.attributes = attributes;
        this.orgUnit = orgUnit;
    }

    public TrackedEntityInstance(String commcareId) {
        this.commcareId = commcareId;
    }

    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    public String getCommcareId() {
        return commcareId;
    }

    public void setCommcareId(String commcareId) {
        this.commcareId = commcareId;
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
