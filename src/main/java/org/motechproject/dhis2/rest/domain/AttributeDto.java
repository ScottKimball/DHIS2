package org.motechproject.dhis2.rest.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class AttributeDto extends BasicLinkedEntity {

    @JsonProperty("organisationUnitGroupSetAttribute")
    private Boolean organisationUnitGroupSetAttribute;
    @JsonProperty("userGroupAttribute")
    private Boolean userGroupAttribute;
    @JsonProperty("dataElementAttribute")
    private Boolean dataElementAttribute;
    @JsonProperty("dataElementGroupAttribute")
    private Boolean dataElementGroupAttribute;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("valueType")
    private String valueType;
    @JsonProperty("indicatorGroupAttribute")
    private Boolean indicatorGroupAttribute;
    @JsonProperty("organisationUnitAttribute")
    private Boolean organisationUnitAttribute;
    @JsonProperty("mandatory")
    private Boolean mandatory;
    @JsonProperty("dataSetAttribute")
    private Boolean dataSetAttribute;
    @JsonProperty("indicatorAttribute")
    private Boolean indicatorAttribute;
    @JsonProperty("userAttribute")
    private Boolean userAttribute;
    @JsonProperty("organisationUnitGroupAttribute")
    private Boolean organisationUnitGroupAttribute;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("userGroupAccesses")
    private List<Object> userGroupAccesses = new ArrayList<Object>();

    /**
     *
     * @return
     *     The organisationUnitGroupSetAttribute
     */
    @JsonProperty("organisationUnitGroupSetAttribute")
    public Boolean getOrganisationUnitGroupSetAttribute() {
        return organisationUnitGroupSetAttribute;
    }

    /**
     *
     * @param organisationUnitGroupSetAttribute
     *     The organisationUnitGroupSetAttribute
     */
    @JsonProperty("organisationUnitGroupSetAttribute")
    public void setOrganisationUnitGroupSetAttribute(Boolean organisationUnitGroupSetAttribute) {
        this.organisationUnitGroupSetAttribute = organisationUnitGroupSetAttribute;
    }

    /**
     *
     * @return
     *     The userGroupAttribute
     */
    @JsonProperty("userGroupAttribute")
    public Boolean getUserGroupAttribute() {
        return userGroupAttribute;
    }

    /**
     *
     * @param userGroupAttribute
     *     The userGroupAttribute
     */
    @JsonProperty("userGroupAttribute")
    public void setUserGroupAttribute(Boolean userGroupAttribute) {
        this.userGroupAttribute = userGroupAttribute;
    }

    /**
     *
     * @return
     *     The dataElementAttribute
     */
    @JsonProperty("dataElementAttribute")
    public Boolean getDataElementAttribute() {
        return dataElementAttribute;
    }

    /**
     *
     * @param dataElementAttribute
     *     The dataElementAttribute
     */
    @JsonProperty("dataElementAttribute")
    public void setDataElementAttribute(Boolean dataElementAttribute) {
        this.dataElementAttribute = dataElementAttribute;
    }

    /**
     *
     * @return
     *     The dataElementGroupAttribute
     */
    @JsonProperty("dataElementGroupAttribute")
    public Boolean getDataElementGroupAttribute() {
        return dataElementGroupAttribute;
    }

    /**
     *
     * @param dataElementGroupAttribute
     *     The dataElementGroupAttribute
     */
    @JsonProperty("dataElementGroupAttribute")
    public void setDataElementGroupAttribute(Boolean dataElementGroupAttribute) {
        this.dataElementGroupAttribute = dataElementGroupAttribute;
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
     *     The indicatorGroupAttribute
     */
    @JsonProperty("indicatorGroupAttribute")
    public Boolean getIndicatorGroupAttribute() {
        return indicatorGroupAttribute;
    }

    /**
     *
     * @param indicatorGroupAttribute
     *     The indicatorGroupAttribute
     */
    @JsonProperty("indicatorGroupAttribute")
    public void setIndicatorGroupAttribute(Boolean indicatorGroupAttribute) {
        this.indicatorGroupAttribute = indicatorGroupAttribute;
    }

    /**
     *
     * @return
     *     The organisationUnitAttribute
     */
    @JsonProperty("organisationUnitAttribute")
    public Boolean getOrganisationUnitAttribute() {
        return organisationUnitAttribute;
    }

    /**
     *
     * @param organisationUnitAttribute
     *     The organisationUnitAttribute
     */
    @JsonProperty("organisationUnitAttribute")
    public void setOrganisationUnitAttribute(Boolean organisationUnitAttribute) {
        this.organisationUnitAttribute = organisationUnitAttribute;
    }

    /**
     *
     * @return
     *     The mandatory
     */
    @JsonProperty("mandatory")
    public Boolean getMandatory() {
        return mandatory;
    }

    /**
     *
     * @param mandatory
     *     The mandatory
     */
    @JsonProperty("mandatory")
    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    /**
     *
     * @return
     *     The dataSetAttribute
     */
    @JsonProperty("dataSetAttribute")
    public Boolean getDataSetAttribute() {
        return dataSetAttribute;
    }

    /**
     *
     * @param dataSetAttribute
     *     The dataSetAttribute
     */
    @JsonProperty("dataSetAttribute")
    public void setDataSetAttribute(Boolean dataSetAttribute) {
        this.dataSetAttribute = dataSetAttribute;
    }

    /**
     *
     * @return
     *     The indicatorAttribute
     */
    @JsonProperty("indicatorAttribute")
    public Boolean getIndicatorAttribute() {
        return indicatorAttribute;
    }

    /**
     *
     * @param indicatorAttribute
     *     The indicatorAttribute
     */
    @JsonProperty("indicatorAttribute")
    public void setIndicatorAttribute(Boolean indicatorAttribute) {
        this.indicatorAttribute = indicatorAttribute;
    }

    /**
     *
     * @return
     *     The userAttribute
     */
    @JsonProperty("userAttribute")
    public Boolean getUserAttribute() {
        return userAttribute;
    }

    /**
     *
     * @param userAttribute
     *     The userAttribute
     */
    @JsonProperty("userAttribute")
    public void setUserAttribute(Boolean userAttribute) {
        this.userAttribute = userAttribute;
    }

    /**
     *
     * @return
     *     The organisationUnitGroupAttribute
     */
    @JsonProperty("organisationUnitGroupAttribute")
    public Boolean getOrganisationUnitGroupAttribute() {
        return organisationUnitGroupAttribute;
    }

    /**
     *
     * @param organisationUnitGroupAttribute
     *     The organisationUnitGroupAttribute
     */
    @JsonProperty("organisationUnitGroupAttribute")
    public void setOrganisationUnitGroupAttribute(Boolean organisationUnitGroupAttribute) {
        this.organisationUnitGroupAttribute = organisationUnitGroupAttribute;
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
