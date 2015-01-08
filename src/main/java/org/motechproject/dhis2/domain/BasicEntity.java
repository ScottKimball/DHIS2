package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;


/**
 * Created by scott on 1/6/15.
 */

@Entity
public class BasicEntity {

    private String name;
    private String uuid;

    public BasicEntity() {
    }

    public BasicEntity(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public BasicEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
