package org.motechproject.dhis2.it;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.domain.Settings;

import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.event.EventSubjects;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.dhis2.rest.domain.AttributeDto;
import org.motechproject.dhis2.service.SettingsService;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.EventRelay;
import org.motechproject.testing.osgi.BasePaxIT;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.motechproject.testing.osgi.http.SimpleHttpClient;
import org.motechproject.testing.osgi.http.SimpleHttpServer;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.springframework.beans.factory.annotation.Qualifier;


import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class EventHandlerBundleIT extends BasePaxIT {

    private static final String ENTITY_TYPE_ID = "entityUUID"; // Person
    private static final String INSTANCE_EXT_ID = "externalId";
    private static final String INSTANCE_DHIS2_ID = "dhis2ID";
    private static final String ORGUNIT_NAME = "OrgUnitName";
    private static final String ORGUNIT_ID = "OrgUnitID";
    private static final String PROGRAM_ID = "programID";
    private static final String DATE = "date";
    private static final String STAGE_ID ="stageID";
    private static final String ATTRIBUTE_UUID_1 = "attributeUuid1";
    private static final String ATTRIBUTE_VALUE_1 = "attributeValue1";
    private static final String ATTRIBUTE_UUID_2 = "attributeUuid2";
    private static final String ATTRIBUTE_VALUE_2 = "attributeValue2";

    @Inject
    private OrgUnitDataService orgUnitDataService;

    @Inject
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;

    @Inject
    EventRelay relay;

    @Inject
    @Qualifier("dhis2SettingsService")
    private SettingsService settingsService;

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

        final String responseBody = "{\"status\":\"SUCCESS\",\"importCount\":{\"imported\":1,\"updated\":0,\"ignored\"" +
                ":0,\"deleted\":0},\"reference\":\"IbqmvQFz0zW\"}{\"status\":\"SUCCESS\",\"importCount\":{\"imported\"" +
                ":1,\"updated\":0,\"ignored\":0,\"deleted\":0},\"reference\":\"GmHEBGJtymq\"}";

        AttributeDto testAttributeDto1 = new AttributeDto();
        testAttributeDto1.setAttribute(ATTRIBUTE_UUID_1);
        testAttributeDto1.setValue(ATTRIBUTE_VALUE_1);

        AttributeDto testAttributeDto2 = new AttributeDto();
        testAttributeDto2.setAttribute(ATTRIBUTE_UUID_2);
        testAttributeDto2.setAttribute(ATTRIBUTE_VALUE_2);

        Map<String, Object> params = new HashMap<>();
        params.put(EventParams.ENTITY_TYPE, ENTITY_TYPE_ID);
        params.put(EventParams.EXTERNAL_ID, INSTANCE_EXT_ID);
        params.put(EventParams.LOCATION, ORGUNIT_NAME);
        params.put(testAttributeDto1.getAttribute(), testAttributeDto1.getValue());
        params.put(testAttributeDto2.getAttribute(), testAttributeDto2.getValue());


        MotechEvent event = new MotechEvent(EventSubjects.CREATE_ENTITY,params);
        relay.sendEventMessage(event);

        SimpleHttpServer simpleServer = SimpleHttpServer.getInstance();
        simpleServer.start(settingsService.getSettings().getURIForResource("trackedEntityInstances"),201,responseBody);

        relay.sendEventMessage(event);
        assertNotNull(trackedEntityInstanceDataService.findByExternalName(INSTANCE_EXT_ID));


    }

    private void populateDatabase () {
        orgUnitDataService.create(new OrgUnit(ORGUNIT_NAME,ORGUNIT_ID));


    }

    private void clearDatabase () {
        orgUnitDataService.deleteAll();
        trackedEntityInstanceDataService.deleteAll();

    }


}
