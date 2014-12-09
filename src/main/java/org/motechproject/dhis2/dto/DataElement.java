package org.motechproject.dhis2.dto;

/**
 * Created by scott on 12/9/14.
 *
 * This class is used only to avoid confusion between
 * data elements and attributes. As far as this module is concerned
 * they are functionally equivalent.
 */
public class DataElement extends Attribute {

    public DataElement (String name, String dhis2Uuid, String value) {
        super(name,dhis2Uuid,value);
    }
}
