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
        "lastUpdated",
        "id",
        "created",
        "name",
        "shortName",
        "href",
        "dataDimension",
        "dimension",
        "description",
        "externalAccess",
        "displayName",
        "compulsory",
        "access",
        "dataElementGroups",
        "items",
        "userGroupAccesses"
})
public class DataElementGroupSetDto {

    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonProperty("id")
    private String id;
    @JsonProperty("created")
    private String created;
    @JsonProperty("name")
    private String name;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("href")
    private String href;
    @JsonProperty("dataDimension")
    private Boolean dataDimension;
    @JsonProperty("dimension")
    private String dimension;
    @JsonProperty("description")
    private String description;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("compulsory")
    private Boolean compulsory;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("dataElementGroups")
    private List<DataElementGroupDto> dataElementGroupDtos = new ArrayList<DataElementGroupDto>();
    @JsonProperty("items")
    private List<ItemDto> itemDtos = new ArrayList<ItemDto>();
    @JsonProperty("userGroupAccesses")
    private List<Object> userGroupAccesses = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The dataDimension
     */
    @JsonProperty("dataDimension")
    public Boolean getDataDimension() {
        return dataDimension;
    }

    /**
     *
     * @param dataDimension
     *     The dataDimension
     */
    @JsonProperty("dataDimension")
    public void setDataDimension(Boolean dataDimension) {
        this.dataDimension = dataDimension;
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
     *     The dataElementGroups
     */
    @JsonProperty("dataElementGroups")
    public List<DataElementGroupDto> getDataElementGroupDtos() {
        return dataElementGroupDtos;
    }

    /**
     *
     * @param dataElementGroupDtos
     *     The dataElementGroups
     */
    @JsonProperty("dataElementGroups")
    public void setDataElementGroupDtos(List<DataElementGroupDto> dataElementGroupDtos) {
        this.dataElementGroupDtos = dataElementGroupDtos;
    }

    /**
     *
     * @return
     *     The items
     */
    @JsonProperty("items")
    public List<ItemDto> getItemDtos() {
        return itemDtos;
    }

    /**
     *
     * @param itemDtos
     *     The items
     */
    @JsonProperty("items")
    public void setItemDtos(List<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
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
        return new HashCodeBuilder().append(lastUpdated).append(id).append(created).append(name).append(shortName).append(href).append(dataDimension).append(dimension).append(description).append(externalAccess).append(displayName).append(compulsory).append(accessDto).append(dataElementGroupDtos).append(itemDtos).append(userGroupAccesses).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DataElementGroupSetDto) == false) {
            return false;
        }
        DataElementGroupSetDto rhs = ((DataElementGroupSetDto) other);
        return new EqualsBuilder().append(lastUpdated, rhs.lastUpdated).append(id, rhs.id).append(created, rhs.created).append(name, rhs.name).append(shortName, rhs.shortName).append(href, rhs.href).append(dataDimension, rhs.dataDimension).append(dimension, rhs.dimension).append(description, rhs.description).append(externalAccess, rhs.externalAccess).append(displayName, rhs.displayName).append(compulsory, rhs.compulsory).append(accessDto, rhs.accessDto).append(dataElementGroupDtos, rhs.dataElementGroupDtos).append(itemDtos, rhs.itemDtos).append(userGroupAccesses, rhs.userGroupAccesses).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
