package org.motechproject.dhis2.rest.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class TrackedEntityAttributeDto extends BasicLinkedEntity {

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
    @JsonProperty("trackedEntityAttributeGroup")
    private TrackedEntityAttributeGroupsDto trackedEntityAttributeGroup;
    @JsonProperty("expression")
    private String expression;
    @JsonProperty("dimensionType")
    private String dimensionType;
    @JsonProperty("optionSet")
    private OptionSetDto optionSet;
    @JsonProperty("user")
    private UserDto user;
    @JsonProperty("publicAccess")
    private String publicAccess;

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

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getDimensionType() {
        return dimensionType;
    }

    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType;
    }

    public OptionSetDto getOptionSet() {
        return optionSet;
    }

    public void setOptionSet(OptionSetDto optionSet) {
        this.optionSet = optionSet;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public TrackedEntityAttributeGroupsDto getTrackedEntityAttributeGroup() {
        return trackedEntityAttributeGroup;
    }

    public void setTrackedEntityAttributeGroup(TrackedEntityAttributeGroupsDto trackedEntityAttributeGroup) {
        this.trackedEntityAttributeGroup = trackedEntityAttributeGroup;
    }

    public String getPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(String publicAccess) {
        this.publicAccess = publicAccess;
    }
}
