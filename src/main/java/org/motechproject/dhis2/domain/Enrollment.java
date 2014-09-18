package org.motechproject.dhis2.domain;

import org.joda.time.DateTime;

/**
 * Created by scott on 9/10/14.
 */


public class Enrollment {

    private Program program;
    private TrackedEntityInstance trackedEntityInstance;
    private OrgUnit orgUnit;
    private DateTime date;

    public Enrollment(Program program, TrackedEntityInstance trackedEntityInstance, OrgUnit orgUnit , DateTime date) {
        this.program = program;
        this.trackedEntityInstance = trackedEntityInstance;
        this.orgUnit = orgUnit;
        this.date = date;
    }

    public String toJson () {
        return null;
        // TODO:
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public TrackedEntityInstance getTrackedEntityInstance() {
        return trackedEntityInstance;
    }

    public void setTrackedEntityInstance(TrackedEntityInstance trackedEntityInstance) {
        this.trackedEntityInstance = trackedEntityInstance;
    }

    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
