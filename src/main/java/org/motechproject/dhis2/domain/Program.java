package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scott on 1/6/15.
 */

@Entity
public class Program {

    @Field
    private String name;

    @Field
    private String id;

    @Field
    private TrackedEntity trackedEntity;

    @Field
    private List<Stage> stages;

    @Field
    private List<TrackedEntityAttribute> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
