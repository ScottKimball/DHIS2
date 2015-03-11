package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.rest.domain.ProgramDto;

import java.util.List;

public interface ProgramService {
    List<Program> findAll();
    List<Program> findByRegistration(boolean registration);
    Program findById(String id);
    Program createFromDetails(ProgramDto details);
    void update(Program program);
    void delete(Program program);
    void deleteAll();
}
