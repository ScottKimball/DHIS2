package org.motechproject.dhis2.rest.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A class to model program stages returned by the DHIS2 API.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramStageDto extends BaseDto {

    private ProgramDto program;
    private List<ProgramStageDataElementDto> programStageDataElements = new ArrayList<ProgramStageDataElementDto>();

    public ProgramDto getProgram() {
        return program;
    }

    public void setProgram(ProgramDto program) {
        this.program = program;
    }

    public List<ProgramStageDataElementDto> getProgramStageDataElements() {
        return programStageDataElements;
    }

    public void setProgramStageDataElements(List<ProgramStageDataElementDto> programStageDataElements) {
        this.programStageDataElements = programStageDataElements;
    }
}
