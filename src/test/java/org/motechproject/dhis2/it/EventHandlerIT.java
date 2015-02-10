package org.motechproject.dhis2.it;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.domain.Settings;
import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.dto.builder.DtoBuilder;
import org.motechproject.dhis2.dto.impl.AttributeDto;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.event.EventHandler;
import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.event.EventSubjects;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.dhis2.service.DataTransferService;
import org.motechproject.dhis2.service.InstanceCreationService;
import org.motechproject.dhis2.service.SettingsService;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.EventListener;
import org.motechproject.event.listener.EventRelay;
import org.motechproject.testing.osgi.BasePaxIT;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by scott on 1/22/15.
 */

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class EventHandlerIT extends BasePaxIT {

    private static final String ENTITY_TYPE_ID = "entityUUID"; // Person
    private static final String INSTANCE_EXT_ID = "externalId";
    private static final String INSTANCE_DHIS2_ID = "dhis2ID";
    private static final String ORGUNIT_NAME = "OrgUnitName";
    private static final String ORGUNIT_ID = "OrgUnitID";
    private static final String PROGRAM_ID = "programID";
    private static final String DATE = "date";
    private static final String STAGE_ID ="stageID";
    private static final String ATTRIBUTE_UUID_1 = "attributeUuid1";
    private static final String ATTRIUBTE_VALUE_1 = "attributeValue1";
    private static final String ATTRIBUTE_UUID_2 = "attributeUuid2";
    private static final String ATTRIUBTE_VALUE_2 = "attributeValue2";




    @Inject
    OrgUnitDataService orgUnitDataService;

    @Inject
    TrackedEntityInstanceDataService trackedEntityInstanceDataService;


    @Inject
    EventRelay eventRelay;



    @Inject
    @Qualifier("dhis2SettingsService")
    SettingsService settingsService;





    @Before
    public void setup() {

        Settings settings = new Settings("http://fakeurl.fake","name","password");
        settingsService.updateSettings(settings);
        clearDatabase();
        populateDatabase();

    }

    @After
    public void tearDown () {
        clearDatabase();
    }


    @Test
    public void testHandleRegistration() throws Exception{

        AttributeDto testAttributeDto1 = new AttributeDto(null, ATTRIBUTE_UUID_1 , ATTRIUBTE_VALUE_1);
        AttributeDto testAttributeDto2 = new AttributeDto(null,ATTRIBUTE_UUID_2, ATTRIUBTE_VALUE_2);

        Map<String, Object> params = new HashMap<>();
        params.put(EventParams.ENTITY_TYPE, ENTITY_TYPE_ID);
        params.put(EventParams.EXTERNAL_ID, INSTANCE_EXT_ID);
        params.put(EventParams.LOCATION, ORGUNIT_NAME);
        params.put(testAttributeDto1.getDhis2Uuid(), testAttributeDto1.getValue());
        params.put(testAttributeDto2.getDhis2Uuid(), testAttributeDto2.getValue());
        params.put("nullValue", null);

        MotechEvent event = new MotechEvent(EventSubjects.REGISTER_ENTITY,params);
        eventRelay.sendEventMessage(event);





    }

    private void populateDatabase () {
        orgUnitDataService.create(new OrgUnit(ORGUNIT_NAME,ORGUNIT_ID));
        trackedEntityInstanceDataService.create(new TrackedEntityInstanceMapper(INSTANCE_EXT_ID,INSTANCE_DHIS2_ID));

    }

    private void clearDatabase () {
        orgUnitDataService.deleteAll();
        trackedEntityInstanceDataService.deleteAll();

    }

}
