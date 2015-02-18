package org.motechproject.dhis2.rest.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class CategoryComboDto extends BasicLinkedEntity {

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

}
