package org.motechproject.dhis2.rest.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        "id",
        "created",
        "name",
        "href",
        "lastUpdated",
        "shortName",
        "unique",
        "programScope",
        "displayInListNoProgram",
        "orgunitScope",
        "displayOnVisitSchedule",
        "description",
        "externalAccess",
        "valueType",
        "confidential",
        "inherit",
        "sortOrderInVisitSchedule",
        "dimension",
        "displayName",
        "sortOrderInListNoProgram",
        "access",
        "items",
        "userGroupAccesses"
})
public class TrackedEntityAttributeDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("created")
    private String created;
    @JsonProperty("name")
    private String name;
    @JsonProperty("href")
    private String href;
    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("unique")
    private Boolean unique;
    @JsonProperty("programScope")
    private Boolean programScope;
    @JsonProperty("displayInListNoProgram")
    private Boolean displayInListNoProgram;
    @JsonProperty("orgunitScope")
    private Boolean orgunitScope;
    @JsonProperty("displayOnVisitSchedule")
    private Boolean displayOnVisitSchedule;
    @JsonProperty("description")
    private String description;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("valueType")
    private String valueType;
    @JsonProperty("confidential")
    private Boolean confidential;
    @JsonProperty("inherit")
    private Boolean inherit;
    @JsonProperty("sortOrderInVisitSchedule")
    private Integer sortOrderInVisitSchedule;
    @JsonProperty("dimension")
    private String dimension;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("sortOrderInListNoProgram")
    private Integer sortOrderInListNoProgram;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("items")
    private List<Object> items = new ArrayList<Object>();
    @JsonProperty("userGroupAccesses")
    private List<Object> userGroupAccesses = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The created
     */
    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     *     The created
     */
    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     *
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The href
     */
    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    /**
     *
     * @param href
     *     The href
     */
    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    /**
     *
     * @return
     *     The lastUpdated
     */
    @JsonProperty("lastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     *
     * @param lastUpdated
     *     The lastUpdated
     */
    @JsonProperty("lastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     *
     * @return
     *     The shortName
     */
    @JsonProperty("shortName")
    public String getShortName() {
        return shortName;
    }

    /**
     *
     * @param shortName
     *     The shortName
     */
    @JsonProperty("shortName")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     *
     * @return
     *     The unique
     */
    @JsonProperty("unique")
    public Boolean getUnique() {
        return unique;
    }

    /**
     *
     * @param unique
     *     The unique
     */
    @JsonProperty("unique")
    public void setUnique(Boolean unique) {
        this.unique = unique;
    }

    /**
     *
     * @return
     *     The programScope
     */
    @JsonProperty("programScope")
    public Boolean getProgramScope() {
        return programScope;
    }

    /**
     *
     * @param programScope
     *     The programScope
     */
    @JsonProperty("programScope")
    public void setProgramScope(Boolean programScope) {
        this.programScope = programScope;
    }

    /**
     *
     * @return
     *     The displayInListNoProgram
     */
    @JsonProperty("displayInListNoProgram")
    public Boolean getDisplayInListNoProgram() {
        return displayInListNoProgram;
    }

    /**
     *
     * @param displayInListNoProgram
     *     The displayInListNoProgram
     */
    @JsonProperty("displayInListNoProgram")
    public void setDisplayInListNoProgram(Boolean displayInListNoProgram) {
        this.displayInListNoProgram = displayInListNoProgram;
    }

    /**
     *
     * @return
     *     The orgunitScope
     */
    @JsonProperty("orgunitScope")
    public Boolean getOrgunitScope() {
        return orgunitScope;
    }

    /**
     *
     * @param orgunitScope
     *     The orgunitScope
     */
    @JsonProperty("orgunitScope")
    public void setOrgunitScope(Boolean orgunitScope) {
        this.orgunitScope = orgunitScope;
    }

    /**
     *
     * @return
     *     The displayOnVisitSchedule
     */
    @JsonProperty("displayOnVisitSchedule")
    public Boolean getDisplayOnVisitSchedule() {
        return displayOnVisitSchedule;
    }

    /**
     *
     * @param displayOnVisitSchedule
     *     The displayOnVisitSchedule
     */
    @JsonProperty("displayOnVisitSchedule")
    public void setDisplayOnVisitSchedule(Boolean displayOnVisitSchedule) {
        this.displayOnVisitSchedule = displayOnVisitSchedule;
    }

    /**
     *
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The externalAccess
     */
    @JsonProperty("externalAccess")
    public Boolean getExternalAccess() {
        return externalAccess;
    }

    /**
     *
     * @param externalAccess
     *     The externalAccess
     */
    @JsonProperty("externalAccess")
    public void setExternalAccess(Boolean externalAccess) {
        this.externalAccess = externalAccess;
    }

    /**
     *
     * @return
     *     The valueType
     */
    @JsonProperty("valueType")
    public String getValueType() {
        return valueType;
    }

    /**
     *
     * @param valueType
     *     The valueType
     */
    @JsonProperty("valueType")
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    /**
     *
     * @return
     *     The confidential
     */
    @JsonProperty("confidential")
    public Boolean getConfidential() {
        return confidential;
    }

    /**
     *
     * @param confidential
     *     The confidential
     */
    @JsonProperty("confidential")
    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

    /**
     *
     * @return
     *     The inherit
     */
    @JsonProperty("inherit")
    public Boolean getInherit() {
        return inherit;
    }

    /**
     *
     * @param inherit
     *     The inherit
     */
    @JsonProperty("inherit")
    public void setInherit(Boolean inherit) {
        this.inherit = inherit;
    }

    /**
     *
     * @return
     *     The sortOrderInVisitSchedule
     */
    @JsonProperty("sortOrderInVisitSchedule")
    public Integer getSortOrderInVisitSchedule() {
        return sortOrderInVisitSchedule;
    }

    /**
     *
     * @param sortOrderInVisitSchedule
     *     The sortOrderInVisitSchedule
     */
    @JsonProperty("sortOrderInVisitSchedule")
    public void setSortOrderInVisitSchedule(Integer sortOrderInVisitSchedule) {
        this.sortOrderInVisitSchedule = sortOrderInVisitSchedule;
    }

    /**
     *
     * @return
     *     The dimension
     */
    @JsonProperty("dimension")
    public String getDimension() {
        return dimension;
    }

    /**
     *
     * @param dimension
     *     The dimension
     */
    @JsonProperty("dimension")
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    /**
     *
     * @return
     *     The displayName
     */
    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     *     The displayName
     */
    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     *     The sortOrderInListNoProgram
     */
    @JsonProperty("sortOrderInListNoProgram")
    public Integer getSortOrderInListNoProgram() {
        return sortOrderInListNoProgram;
    }

    /**
     *
     * @param sortOrderInListNoProgram
     *     The sortOrderInListNoProgram
     */
    @JsonProperty("sortOrderInListNoProgram")
    public void setSortOrderInListNoProgram(Integer sortOrderInListNoProgram) {
        this.sortOrderInListNoProgram = sortOrderInListNoProgram;
    }

    /**
     *
     * @return
     *     The access
     */
    @JsonProperty("access")
    public AccessDto getAccessDto() {
        return accessDto;
    }

    /**
     *
     * @param accessDto
     *     The access
     */
    @JsonProperty("access")
    public void setAccessDto(AccessDto accessDto) {
        this.accessDto = accessDto;
    }

    /**
     *
     * @return
     *     The items
     */
    @JsonProperty("items")
    public List<Object> getItems() {
        return items;
    }

    /**
     *
     * @param items
     *     The items
     */
    @JsonProperty("items")
    public void setItems(List<Object> items) {
        this.items = items;
    }

    /**
     *
     * @return
     *     The userGroupAccesses
     */
    @JsonProperty("userGroupAccesses")
    public List<Object> getUserGroupAccesses() {
        return userGroupAccesses;
    }

    /**
     *
     * @param userGroupAccesses
     *     The userGroupAccesses
     */
    @JsonProperty("userGroupAccesses")
    public void setUserGroupAccesses(List<Object> userGroupAccesses) {
        this.userGroupAccesses = userGroupAccesses;
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
        return new HashCodeBuilder().append(id).append(created).append(name).append(href).append(lastUpdated).append(shortName).append(unique).append(programScope).append(displayInListNoProgram).append(orgunitScope).append(displayOnVisitSchedule).append(description).append(externalAccess).append(valueType).append(confidential).append(inherit).append(sortOrderInVisitSchedule).append(dimension).append(displayName).append(sortOrderInListNoProgram).append(accessDto).append(items).append(userGroupAccesses).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TrackedEntityAttributeDto) == false) {
            return false;
        }
        TrackedEntityAttributeDto rhs = ((TrackedEntityAttributeDto) other);
        return new EqualsBuilder().append(id, rhs.id).append(created, rhs.created).append(name, rhs.name).append(href, rhs.href).append(lastUpdated, rhs.lastUpdated).append(shortName, rhs.shortName).append(unique, rhs.unique).append(programScope, rhs.programScope).append(displayInListNoProgram, rhs.displayInListNoProgram).append(orgunitScope, rhs.orgunitScope).append(displayOnVisitSchedule, rhs.displayOnVisitSchedule).append(description, rhs.description).append(externalAccess, rhs.externalAccess).append(valueType, rhs.valueType).append(confidential, rhs.confidential).append(inherit, rhs.inherit).append(sortOrderInVisitSchedule, rhs.sortOrderInVisitSchedule).append(dimension, rhs.dimension).append(displayName, rhs.displayName).append(sortOrderInListNoProgram, rhs.sortOrderInListNoProgram).append(accessDto, rhs.accessDto).append(items, rhs.items).append(userGroupAccesses, rhs.userGroupAccesses).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
