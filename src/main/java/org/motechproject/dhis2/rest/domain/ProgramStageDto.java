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
        "dataEntryType",
        "blockEntryForm",
        "reportDateDescription",
        "displayGenerateEventBox",
        "description",
        "externalAccess",
        "openAfterEnrollment",
        "captureCoordinates",
        "defaultTemplateMessage",
        "remindCompleted",
        "validCompleteOnly",
        "sortOrder",
        "generatedByEnrollmentDate",
        "autoGenerateEvent",
        "allowGenerateNextVisit",
        "reportDateToUse",
        "repeatable",
        "minDaysFromStart",
        "displayName",
        "access",
        "program",
        "programIndicators",
        "trackedEntityInstanceReminders",
        "programStageDataElements",
        "userGroupAccesses",
        "programStageSections"
})
public class ProgramStageDto {

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
    @JsonProperty("dataEntryType")
    private String dataEntryType;
    @JsonProperty("blockEntryForm")
    private Boolean blockEntryForm;
    @JsonProperty("reportDateDescription")
    private String reportDateDescription;
    @JsonProperty("displayGenerateEventBox")
    private Boolean displayGenerateEventBox;
    @JsonProperty("description")
    private String description;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("openAfterEnrollment")
    private Boolean openAfterEnrollment;
    @JsonProperty("captureCoordinates")
    private Boolean captureCoordinates;
    @JsonProperty("defaultTemplateMessage")
    private String defaultTemplateMessage;
    @JsonProperty("remindCompleted")
    private Boolean remindCompleted;
    @JsonProperty("validCompleteOnly")
    private Boolean validCompleteOnly;
    @JsonProperty("sortOrder")
    private Integer sortOrder;
    @JsonProperty("generatedByEnrollmentDate")
    private Boolean generatedByEnrollmentDate;
    @JsonProperty("autoGenerateEvent")
    private Boolean autoGenerateEvent;
    @JsonProperty("allowGenerateNextVisit")
    private Boolean allowGenerateNextVisit;
    @JsonProperty("reportDateToUse")
    private String reportDateToUse;
    @JsonProperty("repeatable")
    private Boolean repeatable;
    @JsonProperty("minDaysFromStart")
    private Integer minDaysFromStart;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("access")
    private AccessDto accessDto;
    @JsonProperty("program")
    private ProgramDto programDto;
    @JsonProperty("programIndicators")
    private List<Object> programIndicators = new ArrayList<Object>();
    @JsonProperty("trackedEntityInstanceReminders")
    private List<TrackedEntityInstanceReminderDto> trackedEntityInstanceReminderDtos = new ArrayList<TrackedEntityInstanceReminderDto>();
    @JsonProperty("programStageDataElements")
    private List<ProgramStageDataElementDto> programStageDataElementDtos = new ArrayList<ProgramStageDataElementDto>();
    @JsonProperty("userGroupAccesses")
    private List<Object> userGroupAccesses = new ArrayList<Object>();
    @JsonProperty("programStageSections")
    private List<Object> programStageSections = new ArrayList<Object>();
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
     *     The dataEntryType
     */
    @JsonProperty("dataEntryType")
    public String getDataEntryType() {
        return dataEntryType;
    }

    /**
     *
     * @param dataEntryType
     *     The dataEntryType
     */
    @JsonProperty("dataEntryType")
    public void setDataEntryType(String dataEntryType) {
        this.dataEntryType = dataEntryType;
    }

    /**
     *
     * @return
     *     The blockEntryForm
     */
    @JsonProperty("blockEntryForm")
    public Boolean getBlockEntryForm() {
        return blockEntryForm;
    }

    /**
     *
     * @param blockEntryForm
     *     The blockEntryForm
     */
    @JsonProperty("blockEntryForm")
    public void setBlockEntryForm(Boolean blockEntryForm) {
        this.blockEntryForm = blockEntryForm;
    }

    /**
     *
     * @return
     *     The reportDateDescription
     */
    @JsonProperty("reportDateDescription")
    public String getReportDateDescription() {
        return reportDateDescription;
    }

    /**
     *
     * @param reportDateDescription
     *     The reportDateDescription
     */
    @JsonProperty("reportDateDescription")
    public void setReportDateDescription(String reportDateDescription) {
        this.reportDateDescription = reportDateDescription;
    }

    /**
     *
     * @return
     *     The displayGenerateEventBox
     */
    @JsonProperty("displayGenerateEventBox")
    public Boolean getDisplayGenerateEventBox() {
        return displayGenerateEventBox;
    }

    /**
     *
     * @param displayGenerateEventBox
     *     The displayGenerateEventBox
     */
    @JsonProperty("displayGenerateEventBox")
    public void setDisplayGenerateEventBox(Boolean displayGenerateEventBox) {
        this.displayGenerateEventBox = displayGenerateEventBox;
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
     *     The openAfterEnrollment
     */
    @JsonProperty("openAfterEnrollment")
    public Boolean getOpenAfterEnrollment() {
        return openAfterEnrollment;
    }

    /**
     *
     * @param openAfterEnrollment
     *     The openAfterEnrollment
     */
    @JsonProperty("openAfterEnrollment")
    public void setOpenAfterEnrollment(Boolean openAfterEnrollment) {
        this.openAfterEnrollment = openAfterEnrollment;
    }

    /**
     *
     * @return
     *     The captureCoordinates
     */
    @JsonProperty("captureCoordinates")
    public Boolean getCaptureCoordinates() {
        return captureCoordinates;
    }

    /**
     *
     * @param captureCoordinates
     *     The captureCoordinates
     */
    @JsonProperty("captureCoordinates")
    public void setCaptureCoordinates(Boolean captureCoordinates) {
        this.captureCoordinates = captureCoordinates;
    }

    /**
     *
     * @return
     *     The defaultTemplateMessage
     */
    @JsonProperty("defaultTemplateMessage")
    public String getDefaultTemplateMessage() {
        return defaultTemplateMessage;
    }

    /**
     *
     * @param defaultTemplateMessage
     *     The defaultTemplateMessage
     */
    @JsonProperty("defaultTemplateMessage")
    public void setDefaultTemplateMessage(String defaultTemplateMessage) {
        this.defaultTemplateMessage = defaultTemplateMessage;
    }

    /**
     *
     * @return
     *     The remindCompleted
     */
    @JsonProperty("remindCompleted")
    public Boolean getRemindCompleted() {
        return remindCompleted;
    }

    /**
     *
     * @param remindCompleted
     *     The remindCompleted
     */
    @JsonProperty("remindCompleted")
    public void setRemindCompleted(Boolean remindCompleted) {
        this.remindCompleted = remindCompleted;
    }

    /**
     *
     * @return
     *     The validCompleteOnly
     */
    @JsonProperty("validCompleteOnly")
    public Boolean getValidCompleteOnly() {
        return validCompleteOnly;
    }

    /**
     *
     * @param validCompleteOnly
     *     The validCompleteOnly
     */
    @JsonProperty("validCompleteOnly")
    public void setValidCompleteOnly(Boolean validCompleteOnly) {
        this.validCompleteOnly = validCompleteOnly;
    }

    /**
     *
     * @return
     *     The sortOrder
     */
    @JsonProperty("sortOrder")
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     *
     * @param sortOrder
     *     The sortOrder
     */
    @JsonProperty("sortOrder")
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     *
     * @return
     *     The generatedByEnrollmentDate
     */
    @JsonProperty("generatedByEnrollmentDate")
    public Boolean getGeneratedByEnrollmentDate() {
        return generatedByEnrollmentDate;
    }

    /**
     *
     * @param generatedByEnrollmentDate
     *     The generatedByEnrollmentDate
     */
    @JsonProperty("generatedByEnrollmentDate")
    public void setGeneratedByEnrollmentDate(Boolean generatedByEnrollmentDate) {
        this.generatedByEnrollmentDate = generatedByEnrollmentDate;
    }

    /**
     *
     * @return
     *     The autoGenerateEvent
     */
    @JsonProperty("autoGenerateEvent")
    public Boolean getAutoGenerateEvent() {
        return autoGenerateEvent;
    }

    /**
     *
     * @param autoGenerateEvent
     *     The autoGenerateEvent
     */
    @JsonProperty("autoGenerateEvent")
    public void setAutoGenerateEvent(Boolean autoGenerateEvent) {
        this.autoGenerateEvent = autoGenerateEvent;
    }

    /**
     *
     * @return
     *     The allowGenerateNextVisit
     */
    @JsonProperty("allowGenerateNextVisit")
    public Boolean getAllowGenerateNextVisit() {
        return allowGenerateNextVisit;
    }

    /**
     *
     * @param allowGenerateNextVisit
     *     The allowGenerateNextVisit
     */
    @JsonProperty("allowGenerateNextVisit")
    public void setAllowGenerateNextVisit(Boolean allowGenerateNextVisit) {
        this.allowGenerateNextVisit = allowGenerateNextVisit;
    }

    /**
     *
     * @return
     *     The reportDateToUse
     */
    @JsonProperty("reportDateToUse")
    public String getReportDateToUse() {
        return reportDateToUse;
    }

    /**
     *
     * @param reportDateToUse
     *     The reportDateToUse
     */
    @JsonProperty("reportDateToUse")
    public void setReportDateToUse(String reportDateToUse) {
        this.reportDateToUse = reportDateToUse;
    }

    /**
     *
     * @return
     *     The repeatable
     */
    @JsonProperty("repeatable")
    public Boolean getRepeatable() {
        return repeatable;
    }

    /**
     *
     * @param repeatable
     *     The repeatable
     */
    @JsonProperty("repeatable")
    public void setRepeatable(Boolean repeatable) {
        this.repeatable = repeatable;
    }

    /**
     *
     * @return
     *     The minDaysFromStart
     */
    @JsonProperty("minDaysFromStart")
    public Integer getMinDaysFromStart() {
        return minDaysFromStart;
    }

    /**
     *
     * @param minDaysFromStart
     *     The minDaysFromStart
     */
    @JsonProperty("minDaysFromStart")
    public void setMinDaysFromStart(Integer minDaysFromStart) {
        this.minDaysFromStart = minDaysFromStart;
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
     *     The program
     */
    @JsonProperty("program")
    public ProgramDto getProgramDto() {
        return programDto;
    }

    /**
     *
     * @param programDto
     *     The program
     */
    @JsonProperty("program")
    public void setProgramDto(ProgramDto programDto) {
        this.programDto = programDto;
    }

    /**
     *
     * @return
     *     The programIndicators
     */
    @JsonProperty("programIndicators")
    public List<Object> getProgramIndicators() {
        return programIndicators;
    }

    /**
     *
     * @param programIndicators
     *     The programIndicators
     */
    @JsonProperty("programIndicators")
    public void setProgramIndicators(List<Object> programIndicators) {
        this.programIndicators = programIndicators;
    }

    /**
     *
     * @return
     *     The trackedEntityInstanceReminders
     */
    @JsonProperty("trackedEntityInstanceReminders")
    public List<TrackedEntityInstanceReminderDto> getTrackedEntityInstanceReminderDtos() {
        return trackedEntityInstanceReminderDtos;
    }

    /**
     *
     * @param trackedEntityInstanceReminderDtos
     *     The trackedEntityInstanceReminders
     */
    @JsonProperty("trackedEntityInstanceReminders")
    public void setTrackedEntityInstanceReminderDtos(List<TrackedEntityInstanceReminderDto> trackedEntityInstanceReminderDtos) {
        this.trackedEntityInstanceReminderDtos = trackedEntityInstanceReminderDtos;
    }

    /**
     *
     * @return
     *     The programStageDataElements
     */
    @JsonProperty("programStageDataElements")
    public List<ProgramStageDataElementDto> getProgramStageDataElementDtos() {
        return programStageDataElementDtos;
    }

    /**
     *
     * @param programStageDataElementDtos
     *     The programStageDataElements
     */
    @JsonProperty("programStageDataElements")
    public void setProgramStageDataElementDtos(List<ProgramStageDataElementDto> programStageDataElementDtos) {
        this.programStageDataElementDtos = programStageDataElementDtos;
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

    /**
     *
     * @return
     *     The programStageSections
     */
    @JsonProperty("programStageSections")
    public List<Object> getProgramStageSections() {
        return programStageSections;
    }

    /**
     *
     * @param programStageSections
     *     The programStageSections
     */
    @JsonProperty("programStageSections")
    public void setProgramStageSections(List<Object> programStageSections) {
        this.programStageSections = programStageSections;
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
        return new HashCodeBuilder().append(id).append(created).append(name).append(href).append(lastUpdated).append(dataEntryType).append(blockEntryForm).append(reportDateDescription).append(displayGenerateEventBox).append(description).append(externalAccess).append(openAfterEnrollment).append(captureCoordinates).append(defaultTemplateMessage).append(remindCompleted).append(validCompleteOnly).append(sortOrder).append(generatedByEnrollmentDate).append(autoGenerateEvent).append(allowGenerateNextVisit).append(reportDateToUse).append(repeatable).append(minDaysFromStart).append(displayName).append(accessDto).append(programDto).append(programIndicators).append(trackedEntityInstanceReminderDtos).append(programStageDataElementDtos).append(userGroupAccesses).append(programStageSections).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProgramStageDto) == false) {
            return false;
        }
        ProgramStageDto rhs = ((ProgramStageDto) other);
        return new EqualsBuilder().append(id, rhs.id).append(created, rhs.created).append(name, rhs.name).append(href, rhs.href).append(lastUpdated, rhs.lastUpdated).append(dataEntryType, rhs.dataEntryType).append(blockEntryForm, rhs.blockEntryForm).append(reportDateDescription, rhs.reportDateDescription).append(displayGenerateEventBox, rhs.displayGenerateEventBox).append(description, rhs.description).append(externalAccess, rhs.externalAccess).append(openAfterEnrollment, rhs.openAfterEnrollment).append(captureCoordinates, rhs.captureCoordinates).append(defaultTemplateMessage, rhs.defaultTemplateMessage).append(remindCompleted, rhs.remindCompleted).append(validCompleteOnly, rhs.validCompleteOnly).append(sortOrder, rhs.sortOrder).append(generatedByEnrollmentDate, rhs.generatedByEnrollmentDate).append(autoGenerateEvent, rhs.autoGenerateEvent).append(allowGenerateNextVisit, rhs.allowGenerateNextVisit).append(reportDateToUse, rhs.reportDateToUse).append(repeatable, rhs.repeatable).append(minDaysFromStart, rhs.minDaysFromStart).append(displayName, rhs.displayName).append(accessDto, rhs.accessDto).append(programDto, rhs.programDto).append(programIndicators, rhs.programIndicators).append(trackedEntityInstanceReminderDtos, rhs.trackedEntityInstanceReminderDtos).append(programStageDataElementDtos, rhs.programStageDataElementDtos).append(userGroupAccesses, rhs.userGroupAccesses).append(programStageSections, rhs.programStageSections).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
