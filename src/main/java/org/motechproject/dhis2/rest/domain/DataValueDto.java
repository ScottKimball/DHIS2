package org.motechproject.dhis2.rest.domain;

/**
 * A class to model data values associated with program stage events in the DHIS2 API.
 */
public class DataValueDto {
    private String value;
    private String dataElement;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDataElement() {
        return dataElement;
    }

    public void setDataElement(String dataElement) {
        this.dataElement = dataElement;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DataValueDto) {
            DataValueDto dto = (DataValueDto) obj;
            return dto.getDataElement().equals(this.getDataElement());
        }

        return false;
    }
}
