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
        "href",
        "publicAccess",
        "dimensionType",
        "skipTotal",
        "externalAccess",
        "displayName",
        "access",
        "user",
        "categoryOptionCombos",
        "categories",
        "userGroupAccesses"
})
public class CategoryComboDto {

    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonProperty("id")
    private String id;
    @JsonProperty("created")
    private String created;
    @JsonProperty("name")
    private String name;
    @JsonProperty("href")
    private String href;
    @JsonProperty("publicAccess")
    private String publicAccess;
    @JsonProperty("dimensionType")
    private String dimensionType;
    @JsonProperty("skipTotal")
    private Boolean skipTotal;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("user")
    private UserDto userDto;
    @JsonProperty("categoryOptionCombos")
    private List<CategoryOptionComboDto> categoryOptionComboDtos = new ArrayList<CategoryOptionComboDto>();
    @JsonProperty("categories")
    private List<CategoryDto> categories = new ArrayList<CategoryDto>();
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
     *     The publicAccess
     */
    @JsonProperty("publicAccess")
    public String getPublicAccess() {
        return publicAccess;
    }

    /**
     *
     * @param publicAccess
     *     The publicAccess
     */
    @JsonProperty("publicAccess")
    public void setPublicAccess(String publicAccess) {
        this.publicAccess = publicAccess;
    }

    /**
     *
     * @return
     *     The dimensionType
     */
    @JsonProperty("dimensionType")
    public String getDimensionType() {
        return dimensionType;
    }

    /**
     *
     * @param dimensionType
     *     The dimensionType
     */
    @JsonProperty("dimensionType")
    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType;
    }

    /**
     *
     * @return
     *     The skipTotal
     */
    @JsonProperty("skipTotal")
    public Boolean getSkipTotal() {
        return skipTotal;
    }

    /**
     *
     * @param skipTotal
     *     The skipTotal
     */
    @JsonProperty("skipTotal")
    public void setSkipTotal(Boolean skipTotal) {
        this.skipTotal = skipTotal;
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
     *     The user
     */
    @JsonProperty("user")
    public UserDto getUserDto() {
        return userDto;
    }

    /**
     *
     * @param userDto
     *     The user
     */
    @JsonProperty("user")
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    /**
     *
     * @return
     *     The categoryOptionCombos
     */
    @JsonProperty("categoryOptionCombos")
    public List<CategoryOptionComboDto> getCategoryOptionComboDtos() {
        return categoryOptionComboDtos;
    }

    /**
     *
     * @param categoryOptionComboDtos
     *     The categoryOptionCombos
     */
    @JsonProperty("categoryOptionCombos")
    public void setCategoryOptionComboDtos(List<CategoryOptionComboDto> categoryOptionComboDtos) {
        this.categoryOptionComboDtos = categoryOptionComboDtos;
    }

    /**
     *
     * @return
     *     The categories
     */
    @JsonProperty("categories")
    public List<CategoryDto> getCategories() {
        return categories;
    }

    /**
     *
     * @param categories
     *     The categories
     */
    @JsonProperty("categories")
    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
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
        return new HashCodeBuilder().append(lastUpdated).append(id).append(created).append(name).append(href).append(publicAccess).append(dimensionType).append(skipTotal).append(externalAccess).append(displayName).append(accessDto).append(userDto).append(categoryOptionComboDtos).append(categories).append(userGroupAccesses).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CategoryComboDto) == false) {
            return false;
        }
        CategoryComboDto rhs = ((CategoryComboDto) other);
        return new EqualsBuilder().append(lastUpdated, rhs.lastUpdated).append(id, rhs.id).append(created, rhs.created).append(name, rhs.name).append(href, rhs.href).append(publicAccess, rhs.publicAccess).append(dimensionType, rhs.dimensionType).append(skipTotal, rhs.skipTotal).append(externalAccess, rhs.externalAccess).append(displayName, rhs.displayName).append(accessDto, rhs.accessDto).append(userDto, rhs.userDto).append(categoryOptionComboDtos, rhs.categoryOptionComboDtos).append(categories, rhs.categories).append(userGroupAccesses, rhs.userGroupAccesses).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
