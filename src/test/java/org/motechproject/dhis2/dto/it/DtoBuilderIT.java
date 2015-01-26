package org.motechproject.dhis2.dto.it;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.motechproject.dhis2.dto.builder.DtoBuilder;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.event.EventSubjects;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.event.MotechEvent;
import org.motechproject.testing.osgi.BasePaxIT;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by scott on 1/22/15.
 */

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class DtoBuilderIT extends BasePaxIT {
    private static final String ENTITY_TYPE_ID = "TestEntityUUID"; // Person
    private static final String EXT_ID = "TestExternalId";
    private static final String LOC = "TestLocation";

    @Mock
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;

    @Mock
    private OrgUnitDataService orgUnitDataService;

    DtoBuilder builder;

    @Before
    public void setup() {
        builder = new DtoBuilder(trackedEntityInstanceDataService,orgUnitDataService);

    }

    @Test
    public void testEntityCreation() throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put(EventParams.ENTITY_TYPE, ENTITY_TYPE_ID);
        params.put(EventParams.EXTERNAL_ID, EXT_ID);
        params.put(EventParams.LOCATION, LOC);
        params.put("attributeUuid1", "attributeValue1");
        params.put("attributeUuid2", "attributeValue2");
        params.put("nullValue", null);

        MotechEvent event = new MotechEvent(EventSubjects.REGISTER_ENTITY,params);

        TrackedEntityInstanceDto instance = (TrackedEntityInstanceDto) builder.createDto(event);

    }
}
