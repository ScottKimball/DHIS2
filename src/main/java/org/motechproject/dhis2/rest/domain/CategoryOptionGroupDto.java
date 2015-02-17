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
        "code",
        "id",
        "created",
        "name",
        "shortName",
        "href",
        "publicAccess",
        "externalAccess",
        "displayName",
        "access",
        "categoryOptionGroupSet",
        "user",
        "categoryOptions",
        "userGroupAccesses"
})
public class CategoryOptionGroupDto {

    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonProperty("code")
    private String code;
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
    @JsonProperty("publicAccess")
    private String publicAccess;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("categoryOptionGroupSet")
    private CategoryOptionGroupSetDto categoryOptionGroupSetDto;
    @JsonProperty("user")
    private UserDto userDto;
    @JsonProperty("categoryOptions")
    private List<CategoryOptionDto> categoryOptionDtos = new ArrayList<CategoryOptionDto>();
    @JsonProperty("userGroupAccesses")
    private List<UserGroupAccessDto> userGroupAccessDtos = new ArrayList<UserGroupAccessDto>();
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
     *     The code
     */
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     *     The code
     */
    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
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
     *     The categoryOptionGroupSet
     */
    @JsonProperty("categoryOptionGroupSet")
    public CategoryOptionGroupSetDto getCategoryOptionGroupSetDto() {
        return categoryOptionGroupSetDto;
    }

    /**
     *
     * @param categoryOptionGroupSetDto
     *     The categoryOptionGroupSet
     */
    @JsonProperty("categoryOptionGroupSet")
    public void setCategoryOptionGroupSetDto(CategoryOptionGroupSetDto categoryOptionGroupSetDto) {
        this.categoryOptionGroupSetDto = categoryOptionGroupSetDto;
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
     *     The categoryOptions
     */
    @JsonProperty("categoryOptions")
    public List<CategoryOptionDto> getCategoryOptionDtos() {
        return categoryOptionDtos;
    }

    /**
     *
     * @param categoryOptionDtos
     *     The categoryOptions
     */
    @JsonProperty("categoryOptions")
    public void setCategoryOptionDtos(List<CategoryOptionDto> categoryOptionDtos) {
        this.categoryOptionDtos = categoryOptionDtos;
    }

    /**
     *
     * @return
     *     The userGroupAccesses
     */
    @JsonProperty("userGroupAccesses")
    public List<UserGroupAccessDto> getUserGroupAccessDtos() {
        return userGroupAccessDtos;
    }

    /**
     *
     * @param userGroupAccessDtos
     *     The userGroupAccesses
     */
    @JsonProperty("userGroupAccesses")
    public void setUserGroupAccessDtos(List<UserGroupAccessDto> userGroupAccessDtos) {
        this.userGroupAccessDtos = userGroupAccessDtos;
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
        return new HashCodeBuilder().append(lastUpdated).append(code).append(id).append(created).append(name).append(shortName).append(href).append(publicAccess).append(externalAccess).append(displayName).append(accessDto).append(categoryOptionGroupSetDto).append(userDto).append(categoryOptionDtos).append(userGroupAccessDtos).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CategoryOptionGroupDto) == false) {
            return false;
        }
        CategoryOptionGroupDto rhs = ((CategoryOptionGroupDto) other);
        return new EqualsBuilder().append(lastUpdated, rhs.lastUpdated).append(code, rhs.code).append(id, rhs.id).append(created, rhs.created).append(name, rhs.name).append(shortName, rhs.shortName).append(href, rhs.href).append(publicAccess, rhs.publicAccess).append(externalAccess, rhs.externalAccess).append(displayName, rhs.displayName).append(accessDto, rhs.accessDto).append(categoryOptionGroupSetDto, rhs.categoryOptionGroupSetDto).append(userDto, rhs.userDto).append(categoryOptionDtos, rhs.categoryOptionDtos).append(userGroupAccessDtos, rhs.userGroupAccessDtos).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
