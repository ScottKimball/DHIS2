package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.IndividualRecord;
import org.motechproject.event.MotechEvent;
import org.springframework.stereotype.Service;

/**
 * Created by scott on 8/26/14.
 */


public interface SendIndividualRecordService {

    public void send (IndividualRecord individualRecord);
}
