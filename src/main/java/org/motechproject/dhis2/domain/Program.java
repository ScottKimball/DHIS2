package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Cascade;
import org.motechproject.mds.annotations.Entity;

import java.util.List;

/**
 * Created by scott on 1/6/15.
 */

@Entity
public class Program extends BasicEntity {


    private TrackedEntity trackedEntity;

    @Cascade(delete = true)
    private List<Stage> stages;

    private List<TrackedEntityAttribute> attributes;

    private boolean singleEvent;

    private boolean registration;


    public boolean isSingleEvent() {
        return singleEvent;
    }

    public void setSingleEvent(boolean singleEvent) {
        this.singleEvent = singleEvent;
    }

    public boolean hasRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    public TrackedEntity getTrackedEntity() {
        return trackedEntity;
    }

    public void setTrackedEntity(TrackedEntity trackedEntity) {
        this.trackedEntity = trackedEntity;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public List<TrackedEntityAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<TrackedEntityAttribute> attributes) {
        this.attributes = attributes;
    }

    public String getuuid() {
        return super.getUuid();
    }

    public void setUuid(String uuid) {
        super.setUuid(uuid);
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }


}
