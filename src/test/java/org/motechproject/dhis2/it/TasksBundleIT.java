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
import org.motechproject.dhis2.repository.DataElementDataService;
import org.motechproject.dhis2.repository.ProgramDataService;
import org.motechproject.dhis2.repository.StageDataService;
import org.motechproject.dhis2.repository.TrackedEntityAttributeDataService;
import org.motechproject.dhis2.repository.TrackedEntityDataService;
import org.motechproject.dhis2.service.Dhis2SchemaService;
import org.motechproject.dhis2.service.TasksService;
import org.motechproject.dhis2.service.impl.TasksServiceImpl;
import org.motechproject.tasks.service.ChannelService;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
    private BundleContext bundleContext;
    @Inject
    private Dhis2SchemaService dhis2SchemaService;


    @Inject
    private TasksService tasksService;

    @Before
    public void setup() {

    }


    @Test
    public void emptyTest () throws Exception {


        //   tasksService.updateChannel();
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

        stages.add(stageNoReg);
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
        programNoRegistration.setName(PROGRAM_REGISTRATION);
        programNoRegistration.setUuid(PROGRAM_REGISTRATION_ID);
        programNoRegistration.setAttributes(attributeList);
        programNoRegistration.setTrackedEntity(trackedEntity);
        programNoRegistration.setRegistration(true);
        programNoRegistration.setSingleEvent(false);
        programNoRegistration.setStages(stages);

        programDataService.create(programNoRegistration);



    }



}
