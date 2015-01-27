package org.motechproject.dhis2.dto;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.dto.builder.DtoBuilder;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.event.EventSubjects;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.event.MotechEvent;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by scott on 1/22/15.
 */


public class DtoBuilderTest {
    private static final String ENTITY_TYPE_ID = "entityUUID"; // Person
    private static final String INSTANCE_EXT_ID = "externalId";
    private static final String INSTANCE_DHIS2_ID = "dhis2ID";
    private static final String ORGUNIT_NAME = "OrgUnitName";
    private static final String ORGUNIT_ID = "OrgUnitID";

    @Mock
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;

    @Mock
    private OrgUnitDataService orgUnitDataService;

    DtoBuilder builder;
    OrgUnit orgUnit;
    TrackedEntityInstanceMapper mapper;

    @Before
    public void setup() {
        orgUnit = new OrgUnit(ORGUNIT_NAME,ORGUNIT_ID);
        mapper = new TrackedEntityInstanceMapper(INSTANCE_EXT_ID,INSTANCE_DHIS2_ID);

        builder = new DtoBuilder(trackedEntityInstanceDataService,orgUnitDataService);


        when(orgUnitDataService.findByName(ORGUNIT_NAME)).thenReturn(orgUnit);
        when(trackedEntityInstanceDataService.findByExternalName(INSTANCE_EXT_ID)).thenReturn(mapper);

    }

    @Test
    public void testEntityCreation() throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put(EventParams.ENTITY_TYPE, ENTITY_TYPE_ID);
        params.put(EventParams.EXTERNAL_ID, INSTANCE_EXT_ID);
        params.put(EventParams.LOCATION, ORGUNIT_NAME);
        params.put("attributeUuid1", "attributeValue1");
        params.put("attributeUuid2", "attributeValue2");
        params.put("nullValue", null);

        MotechEvent event = new MotechEvent(EventSubjects.REGISTER_ENTITY,params);

        TrackedEntityInstanceDto instance = (TrackedEntityInstanceDto) builder.createDto(event);

        assertNotNull(instance);


    }
}
