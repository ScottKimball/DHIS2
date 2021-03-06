package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.Stage;
import org.motechproject.dhis2.rest.domain.ProgramStageDto;

import java.util.List;

/**
 * Manages CRUD operations for a {@link org.motechproject.dhis2.domain.Stage}
 */
public interface StageService {
    List<Stage> findAll();
    Stage findById(String id);
    Stage createFromDetails(ProgramStageDto details, String programId, boolean hasRegistration);
    void update(Stage stage);
    void delete(Stage stage);
    void deleteAll();
}
