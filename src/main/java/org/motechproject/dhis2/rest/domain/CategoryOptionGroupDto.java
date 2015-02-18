package org.motechproject.dhis2.rest.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class CategoryOptionGroupDto extends BasicLinkedEntity {
    @JsonProperty("shortName")
    private String shortName;
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
}
