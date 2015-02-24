package org.motechproject.dhis2.it;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.domain.Stage;
import org.motechproject.dhis2.domain.TrackedEntity;
import org.motechproject.dhis2.domain.TrackedEntityAttribute;
import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.event.EventSubjects;
import org.motechproject.dhis2.repository.DataElementDataService;
import org.motechproject.dhis2.repository.ProgramDataService;
import org.motechproject.dhis2.repository.StageDataService;
import org.motechproject.dhis2.repository.TrackedEntityAttributeDataService;
import org.motechproject.dhis2.repository.TrackedEntityDataService;
import org.motechproject.dhis2.service.TasksService;
import org.motechproject.tasks.domain.ActionEvent;
import org.motechproject.tasks.domain.ActionParameter;
import org.motechproject.tasks.domain.Channel;
import org.motechproject.tasks.service.ChannelService;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by scott on 2/16/15.
 */

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class TasksBundleIT {

    private static final String DATA_ELEMENT_NAME = "DataElementName";
    private static final String DATA_ELEMENT_ID = "DataElementID";
    private static final String ATTRIBUTE_NAME = "AttributeName";
    private static final String ATTRIBUTE_ID = "AttributeID";
    private static final String PROGRAM_REGISTRATION = "ProgramRegistration";
    private static final String PROGRAM_REGISTRATION_ID = "ProgramID";
    private static final String PROGRAM_NO_REGISTRATION = "ProgramNoRegistration";
    private static final String PROGRAM_NO_REGISTRATION_ID = "ProgramNoRegistrationID";
    private static final String TRACKED_ENTITY_NAME = "ProgramName";
    private static final String TRACKED_ENTITY_ID = "ProgramID";
    private static final String STAGE_NAME = "StageName";
    private static final String STAGE_ID = "StageId";
    private static final String STAGE_NAME_NO_REG = "StageNameNoReg";
    private static final String STAGE_ID_NO_REG = "StageIdNoReg";
    private static final String MODULE_NAME = "org.motechproject.DHIS2";
    private static final int ACTION_EVENT_SIZE = 5;


    @Inject
    private DataElementDataService dataElementDataService;
    @Inject
    private ProgramDataService programDataService;
    @Inject
    private TrackedEntityAttributeDataService trackedEntityAttributeDataService;
    @Inject
    private TrackedEntityDataService trackedEntityDataService;
    @Inject
    private StageDataService stageDataService;
    @Inject
    private TasksService tasksService;
    @Inject
    private ChannelService channelService;
    private Logger logger = LoggerFactory.getLogger(TasksBundleIT.class);

    @Before
    public void setup() {
        populateDatabase();
        logger.debug("updating tasks Channel");
        tasksService.updateChannel();
    }



    @Test
    public void testTaskActionsAreCorrect() throws Exception {


        Channel taskchannel = channelService.getChannel(MODULE_NAME);

        assertNotNull(taskchannel);
        assertEquals(taskchannel.getModuleName(), MODULE_NAME);
        List<ActionEvent> actionEvents = taskchannel.getActionTaskEvents();
        assertNotNull(actionEvents);

        for (ActionEvent e : actionEvents) {
            logger.debug(e.getDisplayName());
            logger.debug(e.toString() + "\n");
        }
        Iterator<ActionEvent> itr = actionEvents.iterator();

        /*Program enrollments first*/
        ActionEvent event = itr.next();
        logger.debug(event.getDisplayName());

        assertEquals(event.getSubject(), EventSubjects.ENROLL_IN_PROGRAM);

        SortedSet<ActionParameter> actionParameters = event.getActionParameters();
        for (ActionParameter actionParameter : actionParameters) {
            logger.debug(actionParameter.toString());
        }


        assertEquals (actionParameters.size(),6);
        ActionParameter parameter = actionParameters.first();
        assertEquals(parameter.getKey(), EventParams.EXTERNAL_ID);

        parameter = actionParameters.first();
        assertEquals(parameter.getKey(),EventParams.DATE);

        parameter = actionParameters.first();
        assertEquals(parameter.getKey(),EventParams.PROGRAM);

        parameter = actionParameters.first();
        assertEquals(parameter.getKey(),EventParams.ENTITY_TYPE);

        parameter = actionParameters.first();
        assertEquals(parameter.getKey(),ATTRIBUTE_ID);

        parameter = actionParameters.first();
        assertEquals(parameter.getKey(),EventParams.LOCATION);

        event = itr.next();






        /*Create tracked Entity instance and Enroll in program*/

        /*Stages*/

        /*Create tracked entity instance*/


    }


    private void populateDatabase () {

        /*Data Elements*/
        DataElement dataElement = new DataElement(DATA_ELEMENT_NAME,DATA_ELEMENT_ID);
        dataElementDataService.create(dataElement);
        List<DataElement> dataElements = new ArrayList<>();
        dataElements.add(dataElement);

        /*Tracked Entity Attributes*/
        TrackedEntityAttribute attribute = new TrackedEntityAttribute(ATTRIBUTE_NAME,ATTRIBUTE_ID);
        trackedEntityAttributeDataService.create(attribute);
        List<TrackedEntityAttribute> attributeList = new ArrayList<>();
        attributeList.add(attribute);

        /*Tracked Entity*/
        TrackedEntity trackedEntity = new TrackedEntity(TRACKED_ENTITY_NAME,TRACKED_ENTITY_ID);
        trackedEntityDataService.create(trackedEntity);

        /*Stages*/
        List<Stage> stages = new ArrayList<>();
        Stage stage = new Stage();
        stage.setRegistration(true);
        stage.setUuid(STAGE_ID);
        stage.setName(STAGE_NAME);
        stage.setProgram(PROGRAM_REGISTRATION_ID);
        stage.setDataElements(dataElements);

        stages.add(stage);
        stageDataService.create(stage);

        Stage stageNoReg = new Stage();
        stageNoReg.setRegistration(false);
        stageNoReg.setUuid(STAGE_ID_NO_REG);
        stageNoReg.setName(STAGE_NAME_NO_REG);
        stageNoReg.setProgram(PROGRAM_NO_REGISTRATION_ID);
        stageNoReg.setDataElements(dataElements);

        List<Stage> stagesNoReg = new ArrayList<>();
        stagesNoReg.add(stageNoReg);
        stageDataService.create(stageNoReg);


        /*Programs*/
        Program program = new Program();
        program.setName(PROGRAM_REGISTRATION);
        program.setUuid(PROGRAM_REGISTRATION_ID);
        program.setAttributes(attributeList);
        program.setTrackedEntity(trackedEntity);
        program.setRegistration(true);
        program.setSingleEvent(false);
        program.setStages(stages);

        programDataService.create(program);

        Program programNoRegistration = new Program();
        programNoRegistration.setName(PROGRAM_NO_REGISTRATION);
        programNoRegistration.setUuid(PROGRAM_NO_REGISTRATION_ID);
        programNoRegistration.setAttributes(attributeList);
        programNoRegistration.setRegistration(false);
        programNoRegistration.setSingleEvent(true);
        programNoRegistration.setStages(stagesNoReg);

        programDataService.create(programNoRegistration);

    }
}
