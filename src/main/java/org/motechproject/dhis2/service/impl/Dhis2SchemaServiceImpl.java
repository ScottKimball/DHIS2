package org.motechproject.dhis2.service.impl;


import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.domain.Stage;
import org.motechproject.dhis2.domain.TrackedEntity;
import org.motechproject.dhis2.domain.TrackedEntityAttribute;
import org.motechproject.dhis2.repository.DataElementDataService;
import org.motechproject.dhis2.repository.ProgramDataService;
import org.motechproject.dhis2.repository.TrackedEntityAttributeDataService;
import org.motechproject.dhis2.repository.TrackedEntityDataService;
import org.motechproject.dhis2.service.Dhis2SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by scott on 1/15/15.
 */

@Service
public class Dhis2SchemaServiceImpl implements Dhis2SchemaService {

    @Autowired
    private TrackedEntityAttributeDataService attributeDataService;

    @Autowired
    private TrackedEntityDataService trackedEntityDataService;

    @Autowired
    private ProgramDataService programDataService;

    @Autowired
    private DataElementDataService dataElementDataService;


    @Override
    public List<DataElement> getDataElements() {

        return dataElementDataService.retrieveAll();
    }


    @Override
    public List<TrackedEntity> getTrackedEntities() {
        return trackedEntityDataService.retrieveAll();
    }


    @Override
    public List<TrackedEntityAttribute> getTrackedEntityAttributes() {
        return attributeDataService.retrieveAll();
    }

    @Override
    public List<Program> getPrograms() {


        List<Program> programs = programDataService.retrieveAll();

        /*Single Event without registration removed*/
        Iterator<Program> itr = programs.iterator();
        while (itr.hasNext()) {
            Program p = itr.next();

            if (!p.hasRegistration()) {
                itr.remove();
            }
        }

        return programs;
    }

    @Override
    public List<Stage> getStages() {

        List<Program> programs = programDataService.retrieveAll();

        List<Stage> stages = new ArrayList<>();

        for (Program program : programs) {
            List<Stage> programStages = program.getStages();
            stages.addAll(programStages);

        }

        return stages;
    }


}
