package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

import java.util.List;

/**
 * Created by scott on 1/6/15.
 */
@Entity
public class Stage extends BasicEntity {

    private List<DataElement> dataElements;



    public List<DataElement> getDataElements() {
        return dataElements;
    }

    public void setDataElements(List<DataElement> dataElements) {
        this.dataElements = dataElements;
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
