package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;

/**
 * Created by scott on 1/8/15.
 */

@Entity
public class OrgUnit extends BasicEntity {

    public OrgUnit(String name, String id) {
        super(name, id);
    }
}
