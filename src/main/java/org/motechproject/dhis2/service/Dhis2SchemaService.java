package org.motechproject.dhis2.service;


import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.domain.Stage;
import org.motechproject.dhis2.domain.TrackedEntity;
import org.motechproject.dhis2.domain.TrackedEntityAttribute;

import java.util.List;

/**
 * Created by scott on 1/15/15.
 */
public interface Dhis2SchemaService {

    List<TrackedEntityAttribute> getTrackedEntityAttributes();

    List<TrackedEntity> getTrackedEntities();

    List<Program> getPrograms();

    List<Stage> getStages();

    List<DataElement> getDataElements();


}
