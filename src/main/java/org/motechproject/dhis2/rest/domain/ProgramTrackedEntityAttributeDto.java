package org.motechproject.dhis2.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramTrackedEntityAttributeDto {

    private TrackedEntityAttributeDto trackedEntityAttribute;

    public TrackedEntityAttributeDto getTrackedEntityAttribute() {
        return trackedEntityAttribute;
    }

    public void setTrackedEntityAttribute(TrackedEntityAttributeDto trackedEntityAttribute) {
        this.trackedEntityAttribute = trackedEntityAttribute;
    }
}
