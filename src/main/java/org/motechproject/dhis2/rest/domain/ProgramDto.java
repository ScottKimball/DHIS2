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
        "type",
        "kind",
        "version",
        "dateOfEnrollmentDescription",
        "description",
        "onlyEnrollOnce",
        "externalAccess",
        "displayIncidentDate",
        "dateOfIncidentDescription",
        "registration",
        "selectEnrollmentDatesInFuture",
        "dataEntryMethod",
        "singleEvent",
        "relationshipText",
        "ignoreOverdueEvents",
        "relationshipFromA",
        "displayName",
        "selectIncidentDatesInFuture",
        "trackedEntity",
        "access",
        "validationCriterias",
        "programStages",
        "instanceReminders",
        "organisationUnits",
        "programTrackedEntityAttributes",
        "userRoles",
        "userGroupAccesses"
})
public class ProgramDto {

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
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("dateOfEnrollmentDescription")
    private String dateOfEnrollmentDescription;
    @JsonProperty("description")
    private String description;
    @JsonProperty("onlyEnrollOnce")
    private Boolean onlyEnrollOnce;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("displayIncidentDate")
    private Boolean displayIncidentDate;
    @JsonProperty("dateOfIncidentDescription")
    private String dateOfIncidentDescription;
    @JsonProperty("registration")
    private Boolean registration;
    @JsonProperty("selectEnrollmentDatesInFuture")
    private Boolean selectEnrollmentDatesInFuture;
    @JsonProperty("dataEntryMethod")
    private Boolean dataEntryMethod;
    @JsonProperty("singleEvent")
    private Boolean singleEvent;
    @JsonProperty("relationshipText")
    private String relationshipText;
    @JsonProperty("ignoreOverdueEvents")
    private Boolean ignoreOverdueEvents;
    @JsonProperty("relationshipFromA")
    private Boolean relationshipFromA;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("selectIncidentDatesInFuture")
    private Boolean selectIncidentDatesInFuture;
    @JsonProperty("trackedEntity")
    private TrackedEntityDto trackedEntityDto;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("validationCriterias")
    private List<Object> validationCriterias = new ArrayList<Object>();
    @JsonProperty("programStages")
    private List<ProgramStageDto> programStageDtos = new ArrayList<ProgramStageDto>();
    @JsonProperty("instanceReminders")
    private List<Object> instanceReminders = new ArrayList<Object>();
    @JsonProperty("organisationUnits")
    private List<OrganisationUnitDto> organisationUnitDtos = new ArrayList<OrganisationUnitDto>();
    @JsonProperty("programTrackedEntityAttributes")
    private List<ProgramTrackedEntityAttributeDto> programTrackedEntityAttributeDtos = new ArrayList<ProgramTrackedEntityAttributeDto>();
    @JsonProperty("userRoles")
    private List<UserRoleDto> userRoleDtos = new ArrayList<UserRoleDto>();
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
     *     The type
     */
    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    /**
     *
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     *
     * @return
     *     The kind
     */
    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    /**
     *
     * @param kind
     *     The kind
     */
    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     *
     * @return
     *     The version
     */
    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    /**
     *
     * @param version
     *     The version
     */
    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     *
     * @return
     *     The dateOfEnrollmentDescription
     */
    @JsonProperty("dateOfEnrollmentDescription")
    public String getDateOfEnrollmentDescription() {
        return dateOfEnrollmentDescription;
    }

    /**
     *
     * @param dateOfEnrollmentDescription
     *     The dateOfEnrollmentDescription
     */
    @JsonProperty("dateOfEnrollmentDescription")
    public void setDateOfEnrollmentDescription(String dateOfEnrollmentDescription) {
        this.dateOfEnrollmentDescription = dateOfEnrollmentDescription;
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
     *     The onlyEnrollOnce
     */
    @JsonProperty("onlyEnrollOnce")
    public Boolean getOnlyEnrollOnce() {
        return onlyEnrollOnce;
    }

    /**
     *
     * @param onlyEnrollOnce
     *     The onlyEnrollOnce
     */
    @JsonProperty("onlyEnrollOnce")
    public void setOnlyEnrollOnce(Boolean onlyEnrollOnce) {
        this.onlyEnrollOnce = onlyEnrollOnce;
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
     *     The displayIncidentDate
     */
    @JsonProperty("displayIncidentDate")
    public Boolean getDisplayIncidentDate() {
        return displayIncidentDate;
    }

    /**
     *
     * @param displayIncidentDate
     *     The displayIncidentDate
     */
    @JsonProperty("displayIncidentDate")
    public void setDisplayIncidentDate(Boolean displayIncidentDate) {
        this.displayIncidentDate = displayIncidentDate;
    }

    /**
     *
     * @return
     *     The dateOfIncidentDescription
     */
    @JsonProperty("dateOfIncidentDescription")
    public String getDateOfIncidentDescription() {
        return dateOfIncidentDescription;
    }

    /**
     *
     * @param dateOfIncidentDescription
     *     The dateOfIncidentDescription
     */
    @JsonProperty("dateOfIncidentDescription")
    public void setDateOfIncidentDescription(String dateOfIncidentDescription) {
        this.dateOfIncidentDescription = dateOfIncidentDescription;
    }

    /**
     *
     * @return
     *     The registration
     */
    @JsonProperty("registration")
    public Boolean getRegistration() {
        return registration;
    }

    /**
     *
     * @param registration
     *     The registration
     */
    @JsonProperty("registration")
    public void setRegistration(Boolean registration) {
        this.registration = registration;
    }

    /**
     *
     * @return
     *     The selectEnrollmentDatesInFuture
     */
    @JsonProperty("selectEnrollmentDatesInFuture")
    public Boolean getSelectEnrollmentDatesInFuture() {
        return selectEnrollmentDatesInFuture;
    }

    /**
     *
     * @param selectEnrollmentDatesInFuture
     *     The selectEnrollmentDatesInFuture
     */
    @JsonProperty("selectEnrollmentDatesInFuture")
    public void setSelectEnrollmentDatesInFuture(Boolean selectEnrollmentDatesInFuture) {
        this.selectEnrollmentDatesInFuture = selectEnrollmentDatesInFuture;
    }

    /**
     *
     * @return
     *     The dataEntryMethod
     */
    @JsonProperty("dataEntryMethod")
    public Boolean getDataEntryMethod() {
        return dataEntryMethod;
    }

    /**
     *
     * @param dataEntryMethod
     *     The dataEntryMethod
     */
    @JsonProperty("dataEntryMethod")
    public void setDataEntryMethod(Boolean dataEntryMethod) {
        this.dataEntryMethod = dataEntryMethod;
    }

    /**
     *
     * @return
     *     The singleEvent
     */
    @JsonProperty("singleEvent")
    public Boolean getSingleEvent() {
        return singleEvent;
    }

    /**
     *
     * @param singleEvent
     *     The singleEvent
     */
    @JsonProperty("singleEvent")
    public void setSingleEvent(Boolean singleEvent) {
        this.singleEvent = singleEvent;
    }

    /**
     *
     * @return
     *     The relationshipText
     */
    @JsonProperty("relationshipText")
    public String getRelationshipText() {
        return relationshipText;
    }

    /**
     *
     * @param relationshipText
     *     The relationshipText
     */
    @JsonProperty("relationshipText")
    public void setRelationshipText(String relationshipText) {
        this.relationshipText = relationshipText;
    }

    /**
     *
     * @return
     *     The ignoreOverdueEvents
     */
    @JsonProperty("ignoreOverdueEvents")
    public Boolean getIgnoreOverdueEvents() {
        return ignoreOverdueEvents;
    }

    /**
     *
     * @param ignoreOverdueEvents
     *     The ignoreOverdueEvents
     */
    @JsonProperty("ignoreOverdueEvents")
    public void setIgnoreOverdueEvents(Boolean ignoreOverdueEvents) {
        this.ignoreOverdueEvents = ignoreOverdueEvents;
    }

    /**
     *
     * @return
     *     The relationshipFromA
     */
    @JsonProperty("relationshipFromA")
    public Boolean getRelationshipFromA() {
        return relationshipFromA;
    }

    /**
     *
     * @param relationshipFromA
     *     The relationshipFromA
     */
    @JsonProperty("relationshipFromA")
    public void setRelationshipFromA(Boolean relationshipFromA) {
        this.relationshipFromA = relationshipFromA;
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
     *     The selectIncidentDatesInFuture
     */
    @JsonProperty("selectIncidentDatesInFuture")
    public Boolean getSelectIncidentDatesInFuture() {
        return selectIncidentDatesInFuture;
    }

    /**
     *
     * @param selectIncidentDatesInFuture
     *     The selectIncidentDatesInFuture
     */
    @JsonProperty("selectIncidentDatesInFuture")
    public void setSelectIncidentDatesInFuture(Boolean selectIncidentDatesInFuture) {
        this.selectIncidentDatesInFuture = selectIncidentDatesInFuture;
    }

    /**
     *
     * @return
     *     The trackedEntity
     */
    @JsonProperty("trackedEntity")
    public TrackedEntityDto getTrackedEntityDto() {
        return trackedEntityDto;
    }

    /**
     *
     * @param trackedEntityDto
     *     The trackedEntity
     */
    @JsonProperty("trackedEntity")
    public void setTrackedEntityDto(TrackedEntityDto trackedEntityDto) {
        this.trackedEntityDto = trackedEntityDto;
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
     *     The validationCriterias
     */
    @JsonProperty("validationCriterias")
    public List<Object> getValidationCriterias() {
        return validationCriterias;
    }

    /**
     *
     * @param validationCriterias
     *     The validationCriterias
     */
    @JsonProperty("validationCriterias")
    public void setValidationCriterias(List<Object> validationCriterias) {
        this.validationCriterias = validationCriterias;
    }

    /**
     *
     * @return
     *     The programStages
     */
    @JsonProperty("programStages")
    public List<ProgramStageDto> getProgramStageDtos() {
        return programStageDtos;
    }

    /**
     *
     * @param programStageDtos
     *     The programStages
     */
    @JsonProperty("programStages")
    public void setProgramStageDtos(List<ProgramStageDto> programStageDtos) {
        this.programStageDtos = programStageDtos;
    }

    /**
     *
     * @return
     *     The instanceReminders
     */
    @JsonProperty("instanceReminders")
    public List<Object> getInstanceReminders() {
        return instanceReminders;
    }

    /**
     *
     * @param instanceReminders
     *     The instanceReminders
     */
    @JsonProperty("instanceReminders")
    public void setInstanceReminders(List<Object> instanceReminders) {
        this.instanceReminders = instanceReminders;
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
     *     The programTrackedEntityAttributes
     */
    @JsonProperty("programTrackedEntityAttributes")
    public List<ProgramTrackedEntityAttributeDto> getProgramTrackedEntityAttributeDtos() {
        return programTrackedEntityAttributeDtos;
    }

    /**
     *
     * @param programTrackedEntityAttributeDtos
     *     The programTrackedEntityAttributes
     */
    @JsonProperty("programTrackedEntityAttributes")
    public void setProgramTrackedEntityAttributeDtos(List<ProgramTrackedEntityAttributeDto> programTrackedEntityAttributeDtos) {
        this.programTrackedEntityAttributeDtos = programTrackedEntityAttributeDtos;
    }

    /**
     *
     * @return
     *     The userRoles
     */
    @JsonProperty("userRoles")
    public List<UserRoleDto> getUserRoleDtos() {
        return userRoleDtos;
    }

    /**
     *
     * @param userRoleDtos
     *     The userRoles
     */
    @JsonProperty("userRoles")
    public void setUserRoleDtos(List<UserRoleDto> userRoleDtos) {
        this.userRoleDtos = userRoleDtos;
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
        return new HashCodeBuilder().append(id).append(created).append(name).append(href).append(lastUpdated).append(type).append(kind).append(version).append(dateOfEnrollmentDescription).append(description).append(onlyEnrollOnce).append(externalAccess).append(displayIncidentDate).append(dateOfIncidentDescription).append(registration).append(selectEnrollmentDatesInFuture).append(dataEntryMethod).append(singleEvent).append(relationshipText).append(ignoreOverdueEvents).append(relationshipFromA).append(displayName).append(selectIncidentDatesInFuture).append(trackedEntityDto).append(accessDto).append(validationCriterias).append(programStageDtos).append(instanceReminders).append(organisationUnitDtos).append(programTrackedEntityAttributeDtos).append(userRoleDtos).append(userGroupAccesses).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProgramDto) == false) {
            return false;
        }
        ProgramDto rhs = ((ProgramDto) other);
        return new EqualsBuilder().append(id, rhs.id).append(created, rhs.created).append(name, rhs.name).append(href, rhs.href).append(lastUpdated, rhs.lastUpdated).append(type, rhs.type).append(kind, rhs.kind).append(version, rhs.version).append(dateOfEnrollmentDescription, rhs.dateOfEnrollmentDescription).append(description, rhs.description).append(onlyEnrollOnce, rhs.onlyEnrollOnce).append(externalAccess, rhs.externalAccess).append(displayIncidentDate, rhs.displayIncidentDate).append(dateOfIncidentDescription, rhs.dateOfIncidentDescription).append(registration, rhs.registration).append(selectEnrollmentDatesInFuture, rhs.selectEnrollmentDatesInFuture).append(dataEntryMethod, rhs.dataEntryMethod).append(singleEvent, rhs.singleEvent).append(relationshipText, rhs.relationshipText).append(ignoreOverdueEvents, rhs.ignoreOverdueEvents).append(relationshipFromA, rhs.relationshipFromA).append(displayName, rhs.displayName).append(selectIncidentDatesInFuture, rhs.selectIncidentDatesInFuture).append(trackedEntityDto, rhs.trackedEntityDto).append(accessDto, rhs.accessDto).append(validationCriterias, rhs.validationCriterias).append(programStageDtos, rhs.programStageDtos).append(instanceReminders, rhs.instanceReminders).append(organisationUnitDtos, rhs.organisationUnitDtos).append(programTrackedEntityAttributeDtos, rhs.programTrackedEntityAttributeDtos).append(userRoleDtos, rhs.userRoleDtos).append(userGroupAccesses, rhs.userGroupAccesses).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
