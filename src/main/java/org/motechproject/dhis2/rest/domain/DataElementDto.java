package org.motechproject.dhis2.rest.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class DataElementDto extends BasicLinkedEntity {

    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("zeroIsSignificant")
    private Boolean zeroIsSignificant;
    @JsonProperty("type")
    private String type;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("textType")
    private String textType;
    @JsonProperty("publicAccess")
    private String publicAccess;
    @JsonProperty("aggregationOperator")
    private String aggregationOperator;
    @JsonProperty("url")
    private String url;
    @JsonProperty("numberType")
    private String numberType;
    @JsonProperty("domainType")
    private String domainType;
    @JsonProperty("dimension")
    private String dimension;
    @JsonProperty("dimensionType")
    private String dimensionType;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("formName")
    private String formName;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("categoryCombo")
    private CategoryComboDto categoryComboDto;
    @JsonProperty("user")
    private UserDto userDto;
    @JsonProperty("aggregationLevels")
    private List<Object> aggregationLevels = new ArrayList<Object>();
    @JsonProperty("dataElementGroups")
    private List<DataElementGroupDto> dataElementGroupDtos = new ArrayList<DataElementGroupDto>();
    @JsonProperty("dataSets")
    private List<DataSetDto> dataSetDtos = new ArrayList<DataSetDto>();
    @JsonProperty("attributeValues")
    private List<Object> attributeValues = new ArrayList<Object>();
    @JsonProperty("items")
    private List<Object> items = new ArrayList<Object>();
    @JsonProperty("userGroupAccesses")
    private List<Object> userGroupAccesses = new ArrayList<Object>();
    @JsonProperty("optionSet")
    private OptionSetDto optionSet;
    @JsonProperty("commentOptionSet")
    private OptionSetDto commentOptionSet;
    @JsonProperty("description")
    private String description;


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
     *     The zeroIsSignificant
     */
    @JsonProperty("zeroIsSignificant")
    public Boolean getZeroIsSignificant() {
        return zeroIsSignificant;
    }

    /**
     *
     * @param zeroIsSignificant
     *     The zeroIsSignificant
     */
    @JsonProperty("zeroIsSignificant")
    public void setZeroIsSignificant(Boolean zeroIsSignificant) {
        this.zeroIsSignificant = zeroIsSignificant;
    }

    /**
     *
     * @return
     *     The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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

    @JsonProperty("textType")
    public String getTextType() {
        return textType;
    }

    @JsonProperty("textType")
    public void setTextType(String textType) {
        this.textType = textType;
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
     *     The aggregationOperator
     */
    @JsonProperty("aggregationOperator")
    public String getAggregationOperator() {
        return aggregationOperator;
    }

    /**
     *
     * @param aggregationOperator
     *     The aggregationOperator
     */
    @JsonProperty("aggregationOperator")
    public void setAggregationOperator(String aggregationOperator) {
        this.aggregationOperator = aggregationOperator;
    }

    /**
     *
     * @return
     *     The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     *     The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     *     The numberType
     */
    @JsonProperty("numberType")
    public String getNumberType() {
        return numberType;
    }

    /**
     *
     * @param numberType
     *     The numberType
     */
    @JsonProperty("numberType")
    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    /**
     *
     * @return
     *     The domainType
     */
    @JsonProperty("domainType")
    public String getDomainType() {
        return domainType;
    }

    /**
     *
     * @param domainType
     *     The domainType
     */
    @JsonProperty("domainType")
    public void setDomainType(String domainType) {
        this.domainType = domainType;
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

    @JsonProperty("formName")
    public String getFormName() {
        return formName;
    }

    @JsonProperty("formName")
    public void setFormName(String formName) {
        this.formName = formName;
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
     *     The aggregationLevels
     */
    @JsonProperty("aggregationLevels")
    public List<Object> getAggregationLevels() {
        return aggregationLevels;
    }

    /**
     *
     * @param aggregationLevels
     *     The aggregationLevels
     */
    @JsonProperty("aggregationLevels")
    public void setAggregationLevels(List<Object> aggregationLevels) {
        this.aggregationLevels = aggregationLevels;
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
     *     The dataSets
     */
    @JsonProperty("dataSets")
    public List<DataSetDto> getDataSetDtos() {
        return dataSetDtos;
    }

    /**
     *
     * @param dataSetDtos
     *     The dataSets
     */
    @JsonProperty("dataSets")
    public void setDataSetDtos(List<DataSetDto> dataSetDtos) {
        this.dataSetDtos = dataSetDtos;
    }

    /**
     *
     * @return
     *     The attributeValues
     */
    @JsonProperty("attributeValues")
    public List<Object> getAttributeValues() {
        return attributeValues;
    }

    /**
     *
     * @param attributeValues
     *     The attributeValues
     */
    @JsonProperty("attributeValues")
    public void setAttributeValues(List<Object> attributeValues) {
        this.attributeValues = attributeValues;
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

    @JsonProperty("dimensionType")
    public String getDimensionType() {
        return dimensionType;
    }

    @JsonProperty("dimensionType")
    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType;
    }

    @JsonProperty("optionSet")
    public OptionSetDto getOptionSet() {
        return optionSet;
    }

    @JsonProperty("optionSet")
    public void setOptionSet(OptionSetDto optionSet) {
        this.optionSet = optionSet;
    }

    @JsonProperty("commentOptionSet")
    public OptionSetDto getCommentOptionSet() {
        return commentOptionSet;
    }

    @JsonProperty("commentOptionSet")
    public void setCommentOptionSet(OptionSetDto commentOptionSet) {
        this.commentOptionSet = commentOptionSet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
