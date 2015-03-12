package org.motechproject.dhis2.rest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class TrackedEntityInstanceDto {
    private String trackedEntity;
    private String trackedEntityInstance;
    private String orgUnit;
    private String created;
    private List<AttributeDto> attributes;

    public String getTrackedEntity() {
        return trackedEntity;
    }

    public void setTrackedEntity(String trackedEntity) {
        this.trackedEntity = trackedEntity;
    }

    public String getTrackedEntityInstance() {
        return trackedEntityInstance;
    }

    public void setTrackedEntityInstance(String trackedEntityInstance) {
        this.trackedEntityInstance = trackedEntityInstance;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<AttributeDto> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeDto> attributes) {
        this.attributes = attributes;
    }


    @Override
    public boolean equals(Object obj) {
        TrackedEntityInstanceDto other = (TrackedEntityInstanceDto) obj;

        if (other.getTrackedEntityInstance() != null && this.getTrackedEntityInstance() != null)
            return other.getTrackedEntityInstance().equals(this.getTrackedEntityInstance());

        for (AttributeDto attribute : other.getAttributes() ) {
            if (!(this.getAttributes().contains(attribute)))
                return false;
        }

        return other.getTrackedEntity().equals(this.getTrackedEntity())
                && other.getOrgUnit().equals(this.getOrgUnit());

    }
}
