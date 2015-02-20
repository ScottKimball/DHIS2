package org.motechproject.dhis2.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramStageDataElementDto {

    private ProgramStageDto programStage;
    private DataElementDto dataElement;

    public ProgramStageDto getProgramStage() {
        return programStage;
    }

    public void setProgramStage(ProgramStageDto programStage) {
        this.programStage = programStage;
    }

    public DataElementDto getDataElement() {
        return dataElement;
    }

    public void setDataElement(DataElementDto dataElement) {
        this.dataElement = dataElement;
    }
}
