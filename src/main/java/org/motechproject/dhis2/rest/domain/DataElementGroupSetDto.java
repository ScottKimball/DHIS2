package org.motechproject.dhis2.rest.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class DataElementGroupSetDto extends BasicLinkedEntity {

    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("dataDimension")
    private Boolean dataDimension;
    @JsonProperty("dimension")
    private String dimension;
    @JsonProperty("description")
    private String description;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("compulsory")
    private Boolean compulsory;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("dataElementGroups")
    private List<DataElementGroupDto> dataElementGroupDtos = new ArrayList<DataElementGroupDto>();
    @JsonProperty("items")
    private List<ItemDto> itemDtos = new ArrayList<ItemDto>();
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
     *     The dataDimension
     */
    @JsonProperty("dataDimension")
    public Boolean getDataDimension() {
        return dataDimension;
    }

    /**
     *
     * @param dataDimension
     *     The dataDimension
     */
    @JsonProperty("dataDimension")
    public void setDataDimension(Boolean dataDimension) {
        this.dataDimension = dataDimension;
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
     *     The compulsory
     */
    @JsonProperty("compulsory")
    public Boolean getCompulsory() {
        return compulsory;
    }

    /**
     *
     * @param compulsory
     *     The compulsory
     */
    @JsonProperty("compulsory")
    public void setCompulsory(Boolean compulsory) {
        this.compulsory = compulsory;
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
     *     The items
     */
    @JsonProperty("items")
    public List<ItemDto> getItemDtos() {
        return itemDtos;
    }

    /**
     *
     * @param itemDtos
     *     The items
     */
    @JsonProperty("items")
    public void setItemDtos(List<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
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
