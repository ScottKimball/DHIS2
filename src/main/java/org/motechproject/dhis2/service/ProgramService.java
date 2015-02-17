package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.rest.domain.ProgramDto;

public interface ProgramService {
    Program findById(String id);
    void update(Program program);
    Program createFromDetails(ProgramDto details);
    void deleteAll();
}
