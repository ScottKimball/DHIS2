package org.motechproject.dhis2.rest.domain;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "allowFutureDate",
        "displayInList",
        "mandatory",
        "trackedEntityAttribute"
})
public class ProgramTrackedEntityAttributeDto {

    @JsonProperty("allowFutureDate")
    private Boolean allowFutureDate;
    @JsonProperty("displayInList")
    private Boolean displayInList;
    @JsonProperty("mandatory")
    private Boolean mandatory;
    @JsonProperty("trackedEntityAttribute")
    private TrackedEntityAttributeDto trackedEntityAttributeDto;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The allowFutureDate
     */
    @JsonProperty("allowFutureDate")
    public Boolean getAllowFutureDate() {
        return allowFutureDate;
    }

    /**
     *
     * @param allowFutureDate
     *     The allowFutureDate
     */
    @JsonProperty("allowFutureDate")
    public void setAllowFutureDate(Boolean allowFutureDate) {
        this.allowFutureDate = allowFutureDate;
    }

    /**
     *
     * @return
     *     The displayInList
     */
    @JsonProperty("displayInList")
    public Boolean getDisplayInList() {
        return displayInList;
    }

    /**
     *
     * @param displayInList
     *     The displayInList
     */
    @JsonProperty("displayInList")
    public void setDisplayInList(Boolean displayInList) {
        this.displayInList = displayInList;
    }

    /**
     *
     * @return
     *     The mandatory
     */
    @JsonProperty("mandatory")
    public Boolean getMandatory() {
        return mandatory;
    }

    /**
     *
     * @param mandatory
     *     The mandatory
     */
    @JsonProperty("mandatory")
    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    /**
     *
     * @return
     *     The trackedEntityAttribute
     */
    @JsonProperty("trackedEntityAttribute")
    public TrackedEntityAttributeDto getTrackedEntityAttributeDto() {
        return trackedEntityAttributeDto;
    }

    /**
     *
     * @param trackedEntityAttributeDto
     *     The trackedEntityAttribute
     */
    @JsonProperty("trackedEntityAttribute")
    public void setTrackedEntityAttributeDto(TrackedEntityAttributeDto trackedEntityAttributeDto) {
        this.trackedEntityAttributeDto = trackedEntityAttributeDto;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(allowFutureDate).append(displayInList).append(mandatory).append(trackedEntityAttributeDto).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProgramTrackedEntityAttributeDto) == false) {
            return false;
        }
        ProgramTrackedEntityAttributeDto rhs = ((ProgramTrackedEntityAttributeDto) other);
        return new EqualsBuilder().append(allowFutureDate, rhs.allowFutureDate).append(displayInList, rhs.displayInList).append(mandatory, rhs.mandatory).append(trackedEntityAttributeDto, rhs.trackedEntityAttributeDto).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
