package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.Enrollment;
import org.motechproject.event.MotechEvent;


/**
 * Created by scott on 9/10/14.
 */
public interface EnrollmentService {

    public void send (Enrollment enrollment);

}