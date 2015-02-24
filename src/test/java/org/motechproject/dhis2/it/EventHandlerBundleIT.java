package org.motechproject.dhis2.it;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.domain.Settings;

import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.dto.impl.AttributeDto;
import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.event.EventSubjects;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
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
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by scott on 1/22/15.
 */

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
    private static final String ATTRIUBTE_VALUE_1 = "attributeValue1";
    private static final String ATTRIBUTE_UUID_2 = "attributeUuid2";
    private static final String ATTRIUBTE_VALUE_2 = "attributeValue2";

    @Inject
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;
    @Inject
    EventRelay relay;
    @Inject
    @Qualifier("dhis2SettingsService")
    private SettingsService settingsService;

    @After
    public void tearDown() {
        trackedEntityInstanceDataService.deleteAll();

    }

    @Test
    public void testHandleRegistration() throws Exception{

        final String responseBody = "{\"status\":\"SUCCESS\",\"importCount\":{\"imported\":1,\"updated\":0,\"ignored\"" +
                ":0,\"deleted\":0},\"reference\":\"IbqmvQFz0zW\"}{\"status\":\"SUCCESS\",\"importCount\":{\"imported\"" +
                ":1,\"updated\":0,\"ignored\":0,\"deleted\":0},\"reference\":\"GmHEBGJtymq\"}";


        SimpleHttpServer simpleServer = SimpleHttpServer.getInstance();
        String URL = simpleServer.start("api/trackedEntityInstances",201,responseBody);

        Settings settings = new Settings(URL,"name","password");
        settingsService.updateSettings(settings);

        AttributeDto testAttributeDto1 = new AttributeDto(null, ATTRIBUTE_UUID_1 , ATTRIUBTE_VALUE_1);
        AttributeDto testAttributeDto2 = new AttributeDto(null,ATTRIBUTE_UUID_2, ATTRIUBTE_VALUE_2);


        Map<String, Object> params = new HashMap<>();
        params.put(EventParams.ENTITY_TYPE, ENTITY_TYPE_ID);
        params.put(EventParams.EXTERNAL_ID, INSTANCE_EXT_ID);
        params.put(EventParams.LOCATION, ORGUNIT_NAME);
        params.put(testAttributeDto1.getDhis2Uuid(), testAttributeDto1.getValue());
        params.put(testAttributeDto2.getDhis2Uuid(), testAttributeDto2.getValue());


        MotechEvent event = new MotechEvent(EventSubjects.CREATE_ENTITY,params);
        relay.sendEventMessage(event);


        /*Need to wait for event to be processed*/
        Thread.sleep(1000);

        TrackedEntityInstanceMapper mapper = trackedEntityInstanceDataService.findByExternalName(INSTANCE_EXT_ID);
        assertNotNull(mapper);
        assertEquals(mapper.getExternalName(),INSTANCE_EXT_ID);
        assertNotNull(mapper.getDhis2Uuid());
        assertEquals(mapper.getDhis2Uuid(),"IbqmvQFz0zW");

    }

}
