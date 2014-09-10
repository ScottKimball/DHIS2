package org.motechproject.dhis2.domain;

/**
 * Created by scott on 9/10/14.
 */


public class Enrollment {

    Program program;
    TrackedEntity trackedEntity;
    OrgUnit orgUnit;

    public Enrollment(Program program, TrackedEntity trackedEntity, OrgUnit orgUnit) {
        this.program = program;
        this.trackedEntity = trackedEntity;
        this.orgUnit = orgUnit;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public TrackedEntity getTrackedEntity() {
        return trackedEntity;
    }

    public void setTrackedEntity(TrackedEntity trackedEntity) {
        this.trackedEntity = trackedEntity;
    }

    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }
}
