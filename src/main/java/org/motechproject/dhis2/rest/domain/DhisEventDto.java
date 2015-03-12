package org.motechproject.dhis2.rest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class DhisEventDto {
    private String event;
    private String program;
    private String programStage;
    private String enrollment;
    private String enrollmentStatus;
    private String trackedEntityInstance;
    private String orgUnit;
    private String orgUnitName;
    private String eventDate;
    private String dueDate;
    private CoordinateDto coordinate;
    private String status;
    private String storedBy;
    private List<DataValueDto> dataValues;
    private boolean followup;

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getTrackedEntityInstance() {
        return trackedEntityInstance;
    }

    public void setTrackedEntityInstance(String trackedEntityInstance) {
        this.trackedEntityInstance = trackedEntityInstance;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStoredBy() {
        return storedBy;
    }

    public void setStoredBy(String storedBy) {
        this.storedBy = storedBy;
    }

    public List<DataValueDto> getDataValues() {
        return dataValues;
    }

    public void setDataValues(List<DataValueDto> dataValues) {
        this.dataValues = dataValues;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getProgramStage() {
        return programStage;
    }

    public void setProgramStage(String programStage) {
        this.programStage = programStage;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getOrgUnitName() {
        return orgUnitName;
    }

    public void setOrgUnitName(String orgUnitName) {
        this.orgUnitName = orgUnitName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public CoordinateDto getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(CoordinateDto coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isFollowup() {
        return followup;
    }

    public void setFollowup(boolean followup) {
        this.followup = followup;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DhisEventDto) {
            DhisEventDto dto = (DhisEventDto) obj;

            for (DataValueDto value : dto.getDataValues()) {
                if (!(this.getDataValues().contains(value)))
                    return false;
            }

            return dto.getProgram().equals(this.getProgram())
                    && dto.getTrackedEntityInstance().equals(this.getTrackedEntityInstance())
                    && dto.getOrgUnit().equals(this.getOrgUnit())
                    && dto.getProgramStage().equals(this.getProgramStage());
        }
        return false;
    }
}
