package org.motechproject.dhis2.rest.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class DataElementGroupDto extends BasicLinkedEntity {

    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("publicAccess")
    private String publicAccess;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("dataElementGroupSet")
    private DataElementGroupSetDto dataElementGroupSetDto;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("attributeValues")
    private List<Object> attributeValues = new ArrayList<Object>();
    @JsonProperty("dataElements")
    private List<DataElementDto> dataElementDtos = new ArrayList<DataElementDto>();
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
     *     The dataElementGroupSet
     */
    @JsonProperty("dataElementGroupSet")
    public DataElementGroupSetDto getDataElementGroupSetDto() {
        return dataElementGroupSetDto;
    }

    /**
     *
     * @param dataElementGroupSetDto
     *     The dataElementGroupSet
     */
    @JsonProperty("dataElementGroupSet")
    public void setDataElementGroupSetDto(DataElementGroupSetDto dataElementGroupSetDto) {
        this.dataElementGroupSetDto = dataElementGroupSetDto;
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
     *     The dataElements
     */
    @JsonProperty("dataElements")
    public List<DataElementDto> getDataElementDtos() {
        return dataElementDtos;
    }

    /**
     *
     * @param dataElementDtos
     *     The dataElements
     */
    @JsonProperty("dataElements")
    public void setDataElementDtos(List<DataElementDto> dataElementDtos) {
        this.dataElementDtos = dataElementDtos;
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
