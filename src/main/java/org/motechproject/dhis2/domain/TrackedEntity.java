package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;

/**
 * Created by scott on 1/6/15.
 */

@Entity
public class TrackedEntity extends BasicEntity {

    public TrackedEntity(String name, String id) {
        super(name, id);
    }
}
