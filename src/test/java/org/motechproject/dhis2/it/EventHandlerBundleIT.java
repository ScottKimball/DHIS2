package org.motechproject.dhis2.it;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.dhis2.domain.Settings;
import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.event.EventSubjects;
import org.motechproject.dhis2.rest.domain.AttributeDto;
import org.motechproject.dhis2.service.SettingsService;
import org.motechproject.dhis2.service.TrackedEntityInstanceMapperService;
import org.motechproject.event.MotechEvent;
import org.motechproject.testing.osgi.BasePaxIT;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.motechproject.testing.osgi.http.SimpleHttpServer;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class EventHandlerBundleIT extends BasePaxIT {

    private static final String ENTITY_TYPE_ID = "entityUUID"; // Person
    private static final String INSTANCE_EXT_ID = "externalId";
    private static final String ORGUNIT_NAME = "OrgUnitName";
    private static final String ATTRIBUTE_UUID_1 = "attributeUuid1";
    private static final String ATTRIBUTE_VALUE_1 = "attributeValue1";
    private static final String ATTRIBUTE_UUID_2 = "attributeUuid2";
    private static final String ATTRIBUTE_VALUE_2 = "attributeValue2";

    @Inject
    private TrackedEntityInstanceMapperService trackedEntityInstanceMapperService;

    @Inject
    private SettingsService settingsService;

    @Before
    public void setup() {
        Settings settings = new Settings("http://fakeurl.fake","name","password");
        settingsService.updateSettings(settings);
    }

    @After
    public void tearDown() {
        trackedEntityInstanceMapperService.deleteAll();
    }

    @Test
    public void testHandleRegistration() throws Exception{

        final String responseBody = "{\"status\":\"SUCCESS\",\"importCount\":{\"imported\":1,\"updated\":0,\"ignored\"" +
                ":0,\"deleted\":0},\"reference\":\"IbqmvQFz0zW\"}{\"status\":\"SUCCESS\",\"importCount\":{\"imported\"" +
                ":1,\"updated\":0,\"ignored\":0,\"deleted\":0},\"reference\":\"GmHEBGJtymq\"}";

        AttributeDto testAttributeDto1 = new AttributeDto();
        testAttributeDto1.setAttribute(ATTRIBUTE_UUID_1);
        testAttributeDto1.setValue(ATTRIBUTE_VALUE_1);

        SimpleHttpServer simpleServer = SimpleHttpServer.getInstance();
        String URL = simpleServer.start("api/trackedEntityInstances",201,responseBody);

        Settings settings = new Settings(URL,"name","password");
        settingsService.updateSettings(settings);

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

        /*Need to wait for event to be processed*/
        Thread.sleep(1000);

        TrackedEntityInstanceMapper mapper = trackedEntityInstanceMapperService.findByExternalId(INSTANCE_EXT_ID);
        assertNotNull(mapper);
        assertEquals(mapper.getExternalName(),INSTANCE_EXT_ID);
        assertNotNull(mapper.getDhis2Uuid());
        assertEquals(mapper.getDhis2Uuid(),"IbqmvQFz0zW");
    }
}
