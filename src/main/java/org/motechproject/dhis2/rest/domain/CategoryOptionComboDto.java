package org.motechproject.dhis2.rest.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class CategoryOptionComboDto extends BasicLinkedEntity {

    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("categoryCombo")
    private CategoryComboDto categoryComboDto;
    @JsonProperty("categoryOptions")
    private List<CategoryOptionDto> categoryOptionDtos = new ArrayList<CategoryOptionDto>();
    @JsonProperty("userGroupAccesses")
    private List<Object> userGroupAccesses = new ArrayList<Object>();

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
     *     The categoryCombo
     */
    @JsonProperty("categoryCombo")
    public CategoryComboDto getCategoryComboDto() {
        return categoryComboDto;
    }

    /**
     *
     * @param categoryComboDto
     *     The categoryCombo
     */
    @JsonProperty("categoryCombo")
    public void setCategoryComboDto(CategoryComboDto categoryComboDto) {
        this.categoryComboDto = categoryComboDto;
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
