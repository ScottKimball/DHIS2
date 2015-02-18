package org.motechproject.dhis2.rest.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class OrganisationUnitGroupDto extends BasicLinkedEntity {

    @JsonProperty("shortName")
    private String shortName;
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

}
