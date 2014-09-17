package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.domain.Enrollment;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.ProgramDataService;
import org.motechproject.dhis2.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by scott on 9/10/14.
 */

// TODO:
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

     // program tracked entity orgunit
    private ProgramDataService programDataService;
    private OrgUnitDataService orgUnitDataService;

    @Autowired
    public EnrollmentServiceImpl( ProgramDataService
            programDataService, OrgUnitDataService orgUnitDataService) {
        this.programDataService = programDataService;
        this.orgUnitDataService = orgUnitDataService;
    }


    @Override
    public void send(Enrollment enrollment) {



    }
}
