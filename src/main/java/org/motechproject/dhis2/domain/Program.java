package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Cascade;
import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

import java.util.ArrayList;
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

    public String getName () {
        return super.getName();
    }

    public void setName (String name) {
        super.setName(name);
    }


}
