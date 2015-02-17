package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.Stage;
import org.motechproject.dhis2.rest.domain.ProgramStageDto;

public interface StageService {
    Stage findById(String id);
    Stage createFromDetails(ProgramStageDto details, String programId, boolean hasRegistration);
    void deleteAll();
}
