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
        "level",
        "created",
        "name",
        "href",
        "lastUpdated",
        "code",
        "uuid",
        "shortName",
        "externalAccess",
        "openingDate",
        "active",
        "displayName",
        "parent",
        "access",
        "users",
        "organisationUnitGroups",
        "children",
        "dataSets",
        "attributeValues",
        "userGroupAccesses"
})
public class OrganisationUnitDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("level")
    private Integer level;
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
     *     The level
     */
    @JsonProperty("level")
    public Integer getLevel() {
        return level;
    }

    /**
     *
     * @param level
     *     The level
     */
    @JsonProperty("level")
    public void setLevel(Integer level) {
        this.level = level;
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
        return new HashCodeBuilder().append(id).append(level).append(created).append(name).append(href).append(lastUpdated).append(code).append(uuid).append(shortName).append(externalAccess).append(openingDate).append(active).append(displayName).append(parentDto).append(accessDto).append(users).append(organisationUnitGroupDtos).append(children).append(dataSetDtos).append(attributeValues).append(userGroupAccesses).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrganisationUnitDto) == false) {
            return false;
        }
        OrganisationUnitDto rhs = ((OrganisationUnitDto) other);
        return new EqualsBuilder().append(id, rhs.id).append(level, rhs.level).append(created, rhs.created).append(name, rhs.name).append(href, rhs.href).append(lastUpdated, rhs.lastUpdated).append(code, rhs.code).append(uuid, rhs.uuid).append(shortName, rhs.shortName).append(externalAccess, rhs.externalAccess).append(openingDate, rhs.openingDate).append(active, rhs.active).append(displayName, rhs.displayName).append(parentDto, rhs.parentDto).append(accessDto, rhs.accessDto).append(users, rhs.users).append(organisationUnitGroupDtos, rhs.organisationUnitGroupDtos).append(children, rhs.children).append(dataSetDtos, rhs.dataSetDtos).append(attributeValues, rhs.attributeValues).append(userGroupAccesses, rhs.userGroupAccesses).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
