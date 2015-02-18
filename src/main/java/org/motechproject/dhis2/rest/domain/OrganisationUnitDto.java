package org.motechproject.dhis2.rest.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class OrganisationUnitDto extends BasicLinkedEntity {

    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("openingDate")
    private String openingDate;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("parent")
    private ParentDto parentDto;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("users")
    private List<Object> users = new ArrayList<Object>();
    @JsonProperty("organisationUnitGroups")
    private List<OrganisationUnitGroupDto> organisationUnitGroupDtos = new ArrayList<OrganisationUnitGroupDto>();
    @JsonProperty("children")
    private List<Object> children = new ArrayList<Object>();
    @JsonProperty("dataSets")
    private List<DataSetDto> dataSetDtos = new ArrayList<DataSetDto>();
    @JsonProperty("attributeValues")
    private List<Object> attributeValues = new ArrayList<Object>();
    @JsonProperty("userGroupAccesses")
    private List<Object> userGroupAccesses = new ArrayList<Object>();
    @JsonProperty("closedDate")
    private String closedDate;
    @JsonProperty("type")
    private String type;
    @JsonProperty("level")
    private int level;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("description")
    private String description;
    @JsonProperty("featureType")
    private String featureType;
    @JsonProperty("coordinates")
    private String coordinates;
    @JsonProperty("publicAccess")
    private String publicAccess;
    @JsonProperty("url")
    private String url;
    @JsonProperty("address")
    private String address;
    @JsonProperty("email")
    private String email;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("user")
    private UserDto user;
    @JsonProperty("contactPerson")
    private String contactPerson;

    /**
     *
     * @return
     *     The uuid
     */
    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @param uuid
     *     The uuid
     */
    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
     *     The openingDate
     */
    @JsonProperty("openingDate")
    public String getOpeningDate() {
        return openingDate;
    }

    /**
     *
     * @param openingDate
     *     The openingDate
     */
    @JsonProperty("openingDate")
    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    /**
     *
     * @return
     *     The active
     */
    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    /**
     *
     * @param active
     *     The active
     */
    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
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
     *     The parent
     */
    @JsonProperty("parent")
    public ParentDto getParentDto() {
        return parentDto;
    }

    /**
     *
     * @param parentDto
     *     The parent
     */
    @JsonProperty("parent")
    public void setParentDto(ParentDto parentDto) {
        this.parentDto = parentDto;
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
     *     The users
     */
    @JsonProperty("users")
    public List<Object> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     *     The users
     */
    @JsonProperty("users")
    public void setUsers(List<Object> users) {
        this.users = users;
    }

    /**
     *
     * @return
     *     The organisationUnitGroups
     */
    @JsonProperty("organisationUnitGroups")
    public List<OrganisationUnitGroupDto> getOrganisationUnitGroupDtos() {
        return organisationUnitGroupDtos;
    }

    /**
     *
     * @param organisationUnitGroupDtos
     *     The organisationUnitGroups
     */
    @JsonProperty("organisationUnitGroups")
    public void setOrganisationUnitGroupDtos(List<OrganisationUnitGroupDto> organisationUnitGroupDtos) {
        this.organisationUnitGroupDtos = organisationUnitGroupDtos;
    }

    /**
     *
     * @return
     *     The children
     */
    @JsonProperty("children")
    public List<Object> getChildren() {
        return children;
    }

    /**
     *
     * @param children
     *     The children
     */
    @JsonProperty("children")
    public void setChildren(List<Object> children) {
        this.children = children;
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

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(String publicAccess) {
        this.publicAccess = publicAccess;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}
