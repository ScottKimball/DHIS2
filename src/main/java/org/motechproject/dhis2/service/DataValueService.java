package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.DataValue;

/**
 * Created by scott on 9/8/14.
 */
public interface DataValueService {

    public DataValue create(String name, String value , String orgUnit, String timePeriod);
}
