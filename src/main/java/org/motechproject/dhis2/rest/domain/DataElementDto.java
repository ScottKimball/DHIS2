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
        "code",
        "shortName",
        "zeroIsSignificant",
        "type",
        "externalAccess",
        "publicAccess",
        "aggregationOperator",
        "url",
        "numberType",
        "domainType",
        "dimension",
        "displayName",
        "access",
        "categoryCombo",
        "user",
        "aggregationLevels",
        "dataElementGroups",
        "dataSets",
        "attributeValues",
        "items",
        "userGroupAccesses"
})
public class DataElementDto {

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
    @JsonProperty("code")
    private String code;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("zeroIsSignificant")
    private Boolean zeroIsSignificant;
    @JsonProperty("type")
    private String type;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
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
    @JsonProperty("displayName")
    private String displayName;
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
        return new HashCodeBuilder().append(id).append(created).append(name).append(href).append(lastUpdated).append(code).append(shortName).append(zeroIsSignificant).append(type).append(externalAccess).append(publicAccess).append(aggregationOperator).append(url).append(numberType).append(domainType).append(dimension).append(displayName).append(accessDto).append(categoryComboDto).append(userDto).append(aggregationLevels).append(dataElementGroupDtos).append(dataSetDtos).append(attributeValues).append(items).append(userGroupAccesses).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DataElementDto) == false) {
            return false;
        }
        DataElementDto rhs = ((DataElementDto) other);
        return new EqualsBuilder().append(id, rhs.id).append(created, rhs.created).append(name, rhs.name).append(href, rhs.href).append(lastUpdated, rhs.lastUpdated).append(code, rhs.code).append(shortName, rhs.shortName).append(zeroIsSignificant, rhs.zeroIsSignificant).append(type, rhs.type).append(externalAccess, rhs.externalAccess).append(publicAccess, rhs.publicAccess).append(aggregationOperator, rhs.aggregationOperator).append(url, rhs.url).append(numberType, rhs.numberType).append(domainType, rhs.domainType).append(dimension, rhs.dimension).append(displayName, rhs.displayName).append(accessDto, rhs.accessDto).append(categoryComboDto, rhs.categoryComboDto).append(userDto, rhs.userDto).append(aggregationLevels, rhs.aggregationLevels).append(dataElementGroupDtos, rhs.dataElementGroupDtos).append(dataSetDtos, rhs.dataSetDtos).append(attributeValues, rhs.attributeValues).append(items, rhs.items).append(userGroupAccesses, rhs.userGroupAccesses).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
