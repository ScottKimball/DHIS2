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
        "symbol",
        "externalAccess",
        "displayName",
        "access",
        "organisationUnitGroupSet",
        "organisationUnits",
        "attributeValues",
        "userGroupAccesses"
})
public class OrganisationUnitGroupDto {

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
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("organisationUnitGroupSet")
    private OrganisationUnitGroupSetDto organisationUnitGroupSetDto;
    @JsonProperty("organisationUnits")
    private List<OrganisationUnitDto> organisationUnitDtos = new ArrayList<OrganisationUnitDto>();
    @JsonProperty("attributeValues")
    private List<Object> attributeValues = new ArrayList<Object>();
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
     *     The symbol
     */
    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    /**
     *
     * @param symbol
     *     The symbol
     */
    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
     *     The organisationUnitGroupSet
     */
    @JsonProperty("organisationUnitGroupSet")
    public OrganisationUnitGroupSetDto getOrganisationUnitGroupSetDto() {
        return organisationUnitGroupSetDto;
    }

    /**
     *
     * @param organisationUnitGroupSetDto
     *     The organisationUnitGroupSet
     */
    @JsonProperty("organisationUnitGroupSet")
    public void setOrganisationUnitGroupSetDto(OrganisationUnitGroupSetDto organisationUnitGroupSetDto) {
        this.organisationUnitGroupSetDto = organisationUnitGroupSetDto;
    }

    /**
     *
     * @return
     *     The organisationUnits
     */
    @JsonProperty("organisationUnits")
    public List<OrganisationUnitDto> getOrganisationUnitDtos() {
        return organisationUnitDtos;
    }

    /**
     *
     * @param organisationUnitDtos
     *     The organisationUnits
     */
    @JsonProperty("organisationUnits")
    public void setOrganisationUnitDtos(List<OrganisationUnitDto> organisationUnitDtos) {
        this.organisationUnitDtos = organisationUnitDtos;
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
        return new HashCodeBuilder().append(lastUpdated).append(code).append(id).append(created).append(name).append(shortName).append(href).append(symbol).append(externalAccess).append(displayName).append(accessDto).append(organisationUnitGroupSetDto).append(organisationUnitDtos).append(attributeValues).append(userGroupAccesses).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrganisationUnitGroupDto) == false) {
            return false;
        }
        OrganisationUnitGroupDto rhs = ((OrganisationUnitGroupDto) other);
        return new EqualsBuilder().append(lastUpdated, rhs.lastUpdated).append(code, rhs.code).append(id, rhs.id).append(created, rhs.created).append(name, rhs.name).append(shortName, rhs.shortName).append(href, rhs.href).append(symbol, rhs.symbol).append(externalAccess, rhs.externalAccess).append(displayName, rhs.displayName).append(accessDto, rhs.accessDto).append(organisationUnitGroupSetDto, rhs.organisationUnitGroupSetDto).append(organisationUnitDtos, rhs.organisationUnitDtos).append(attributeValues, rhs.attributeValues).append(userGroupAccesses, rhs.userGroupAccesses).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
