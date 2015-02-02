package org.motechproject.dhis2.tasks;

import org.junit.Test;
import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.domain.Stage;
import org.motechproject.dhis2.domain.TrackedEntity;
import org.motechproject.dhis2.domain.TrackedEntityAttribute;
import org.motechproject.tasks.contract.ActionEventRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by scottkimball on 1/27/15.
 */
public class ProgramTriggerBuilderTest {

    @Test
    public void TestBuildPrograms() throws Exception {
        ProgramTriggerBuilder builder = new ProgramTriggerBuilder();
        List<Program> programs = new ArrayList<Program>();
        List<Stage> stages = new ArrayList<>();
        List<TrackedEntityAttribute> attributes = new ArrayList<>();

        TrackedEntityAttribute attribute1 = new TrackedEntityAttribute("attribute1", "uuid");
        TrackedEntityAttribute attribute2 = new TrackedEntityAttribute("attribute2", "uuid");
        attributes.add(attribute1);
        attributes.add(attribute2);

        TrackedEntity trackedEntity = new TrackedEntity("trackedEntityName","trackedEntityID");

        Program program1 = new Program();
        program1.setName("Program1");
        program1.setUuid("program1UUID");
        program1.setRegistration(true);
        program1.setSingleEvent(false);
        program1.setTrackedEntity(trackedEntity);
        program1.setStages(stages);
        program1.setAttributes(attributes);

        Program program2 = new Program();
        program2.setName("program2");
        program2.setUuid("program2UUID");
        program2.setRegistration(true);
        program2.setSingleEvent(false);
        program2.setTrackedEntity(trackedEntity);
        program2.setStages(stages);
        program2.setAttributes(attributes);

        programs.add(program1);
        programs.add(program2);

        List<ActionEventRequest> actionEventRequests = builder.build(programs);

        assertNotNull(actionEventRequests);

    }
}
