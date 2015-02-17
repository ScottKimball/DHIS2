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
        "sortOrder",
        "displayInReports",
        "allowProvidedElsewhere",
        "compulsory",
        "programStage",
        "dataElement"
})
public class ProgramStageDataElementDto {

    @JsonProperty("allowFutureDate")
    private Boolean allowFutureDate;
    @JsonProperty("sortOrder")
    private Integer sortOrder;
    @JsonProperty("displayInReports")
    private Boolean displayInReports;
    @JsonProperty("allowProvidedElsewhere")
    private Boolean allowProvidedElsewhere;
    @JsonProperty("compulsory")
    private Boolean compulsory;
    @JsonProperty("programStage")
    private ProgramStageDto programStageDto;
    @JsonProperty("dataElement")
    private DataElementDto dataElementDto;
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
     *     The sortOrder
     */
    @JsonProperty("sortOrder")
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     *
     * @param sortOrder
     *     The sortOrder
     */
    @JsonProperty("sortOrder")
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     *
     * @return
     *     The displayInReports
     */
    @JsonProperty("displayInReports")
    public Boolean getDisplayInReports() {
        return displayInReports;
    }

    /**
     *
     * @param displayInReports
     *     The displayInReports
     */
    @JsonProperty("displayInReports")
    public void setDisplayInReports(Boolean displayInReports) {
        this.displayInReports = displayInReports;
    }

    /**
     *
     * @return
     *     The allowProvidedElsewhere
     */
    @JsonProperty("allowProvidedElsewhere")
    public Boolean getAllowProvidedElsewhere() {
        return allowProvidedElsewhere;
    }

    /**
     *
     * @param allowProvidedElsewhere
     *     The allowProvidedElsewhere
     */
    @JsonProperty("allowProvidedElsewhere")
    public void setAllowProvidedElsewhere(Boolean allowProvidedElsewhere) {
        this.allowProvidedElsewhere = allowProvidedElsewhere;
    }

    /**
     *
     * @return
     *     The compulsory
     */
    @JsonProperty("compulsory")
    public Boolean getCompulsory() {
        return compulsory;
    }

    /**
     *
     * @param compulsory
     *     The compulsory
     */
    @JsonProperty("compulsory")
    public void setCompulsory(Boolean compulsory) {
        this.compulsory = compulsory;
    }

    /**
     *
     * @return
     *     The programStage
     */
    @JsonProperty("programStage")
    public ProgramStageDto getProgramStageDto() {
        return programStageDto;
    }

    /**
     *
     * @param programStageDto
     *     The programStage
     */
    @JsonProperty("programStage")
    public void setProgramStageDto(ProgramStageDto programStageDto) {
        this.programStageDto = programStageDto;
    }

    /**
     *
     * @return
     *     The dataElement
     */
    @JsonProperty("dataElement")
    public DataElementDto getDataElementDto() {
        return dataElementDto;
    }

    /**
     *
     * @param dataElementDto
     *     The dataElement
     */
    @JsonProperty("dataElement")
    public void setDataElementDto(DataElementDto dataElementDto) {
        this.dataElementDto = dataElementDto;
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
        return new HashCodeBuilder().append(allowFutureDate).append(sortOrder).append(displayInReports).append(allowProvidedElsewhere).append(compulsory).append(programStageDto).append(dataElementDto).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProgramStageDataElementDto) == false) {
            return false;
        }
        ProgramStageDataElementDto rhs = ((ProgramStageDataElementDto) other);
        return new EqualsBuilder().append(allowFutureDate, rhs.allowFutureDate).append(sortOrder, rhs.sortOrder).append(displayInReports, rhs.displayInReports).append(allowProvidedElsewhere, rhs.allowProvidedElsewhere).append(compulsory, rhs.compulsory).append(programStageDto, rhs.programStageDto).append(dataElementDto, rhs.dataElementDto).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
