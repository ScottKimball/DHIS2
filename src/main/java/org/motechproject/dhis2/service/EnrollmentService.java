package org.motechproject.dhis2.service;

import org.motechproject.dhis2.dto.impl.EnrollmentDto;


/**
 * Created by scott on 9/10/14.
 */
public interface EnrollmentService {

    void send(EnrollmentDto enrollmentDto);

}
