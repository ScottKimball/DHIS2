package org.motechproject.dhis2.dto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.dto.builder.DtoBuilder;
import org.motechproject.dhis2.dto.impl.AttributeDto;
import org.motechproject.dhis2.dto.impl.EnrollmentDto;
import org.motechproject.dhis2.dto.impl.StageDto;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.event.EventSubjects;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.event.MotechEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by scott on 1/22/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class DtoBuilderTest {

    private static final String ENTITY_TYPE_ID = "entityUUID"; // Person
    private static final String INSTANCE_EXT_ID = "externalId";
    private static final String INSTANCE_DHIS2_ID = "dhis2ID";
    private static final String ORGUNIT_NAME = "OrgUnitName";
    private static final String ORGUNIT_ID = "OrgUnitID";
    private static final String PROGRAM_ID = "programID";
    private static final String DATE = "date";
    private static final String STAGE_ID ="stageID";


    private AttributeDto testAttributeDto1;
    private AttributeDto testAttributeDto2;
    private DtoBuilder builder;
    private OrgUnit orgUnit;
    private TrackedEntityInstanceMapper mapper;


    @Mock
    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;

    @Mock
    private OrgUnitDataService orgUnitDataService;


    @Before
    public void setup() {

        orgUnit = new OrgUnit(ORGUNIT_NAME,ORGUNIT_ID);
        mapper = new TrackedEntityInstanceMapper(INSTANCE_EXT_ID,INSTANCE_DHIS2_ID);
        builder = new DtoBuilder(trackedEntityInstanceDataService,orgUnitDataService);

        testAttributeDto1 = new AttributeDto(null, "attributeUuid1", "attributeValue1");
        testAttributeDto2 = new AttributeDto(null,"attributeUuid2","attributeValue2");

        when(orgUnitDataService.findByName(ORGUNIT_NAME)).thenReturn(orgUnit);
        when(trackedEntityInstanceDataService.findByExternalName(INSTANCE_EXT_ID)).thenReturn(mapper);

    }

    @Test
    public void testEntityCreation() throws Exception {

        String toJson = "{\"orgUnit\":\"OrgUnitID\",\"trackedEntity\":\"entityUUID\",\"attributes\":" +
                "[{\"attribute\":\"attributeUuid2\",\"value\":\"attributeValue2\"},{\"attribute\":" +
                "\"attributeUuid1\",\"value\":\"attributeValue1\"}]}";



        Map<String, Object> params = new HashMap<>();
        params.put(EventParams.ENTITY_TYPE, ENTITY_TYPE_ID);
        params.put(EventParams.EXTERNAL_ID, INSTANCE_EXT_ID);
        params.put(EventParams.LOCATION, ORGUNIT_NAME);
        params.put(testAttributeDto1.getDhis2Uuid(), testAttributeDto1.getValue());
        params.put(testAttributeDto2.getDhis2Uuid(), testAttributeDto2.getValue());
        params.put("nullValue", null);

        MotechEvent event = new MotechEvent(EventSubjects.REGISTER_ENTITY,params);

        TrackedEntityInstanceDto instance = (TrackedEntityInstanceDto) builder.createDto(event);

        assertNotNull(instance);
        assertEquals(instance.getTrackedEntityType(), ENTITY_TYPE_ID);
        assertEquals(instance.getExternalId(),INSTANCE_EXT_ID);
        assertEquals(instance.getOrgUnit(),ORGUNIT_ID);

        List<AttributeDto> attributeDtos = instance.getAttributeDtos();
        assertNotNull(attributeDtos);
        assertEquals(2,attributeDtos.size());
        assertTrue(attributeDtos.contains(testAttributeDto1));
        assertTrue(attributeDtos.contains(testAttributeDto2));

        assertEquals(instance.toJson(), toJson);
    }



    @Test
    public void testProgramEnrollment() throws Exception {

        String toJson = "{\"program\":\"programID\",\"trackedEntityInstance\":\"dhis2ID\"," +
                "\"dateOfEnrollment\":\"date\",\"attributes\":[{\"attribute\":\"attributeUuid2\"," +
                "\"value\":\"attributeValue2\"},{\"attribute\":\"attributeUuid1\",\"value\":\"attributeValue1\"}]}";



        Map<String, Object> params = new HashMap<>();
        params.put(EventParams.PROGRAM,PROGRAM_ID);
        params.put(EventParams.EXTERNAL_ID,INSTANCE_EXT_ID);
        params.put(EventParams.DATE,DATE);
        params.put(testAttributeDto1.getDhis2Uuid(), testAttributeDto1.getValue());
        params.put(testAttributeDto2.getDhis2Uuid(), testAttributeDto2.getValue());
        params.put("nullValue", null);

        MotechEvent event = new MotechEvent(EventSubjects.ENROLL_IN_PROGRAM,params);

        EnrollmentDto enrollmentDto = (EnrollmentDto) builder.createDto(event);
        assertNotNull(enrollmentDto);
        assertEquals(enrollmentDto.getProgram(),PROGRAM_ID);
        assertEquals(enrollmentDto.getDate(),DATE);
        assertEquals(enrollmentDto.getTrackedEntityInstance(),INSTANCE_DHIS2_ID);

        List<AttributeDto> attributeDtos = enrollmentDto.getAttributeDtos();

        assertNotNull(attributeDtos);
        assertTrue(attributeDtos.contains(testAttributeDto1));
        assertTrue(attributeDtos.contains(testAttributeDto2));
        assertEquals(2, attributeDtos.size());

        assertEquals(toJson,enrollmentDto.toJson());


    }

    @Test
    public void testStageUpdate() throws Exception {

        String toJson = "{\"program\":\"programID\",\"orgUnit\":\"OrgUnitID\",\"eventDate\":\"date\"," +
                "\"programStage\":\"stageID\",\"trackedEntityInstance\":\"dhis2ID\",\"dataValues\":" +
                "[{\"dataElement\":\"attributeUuid2\",\"value\":\"attributeValue2\"}," +
                "{\"dataElement\":\"attributeUuid1\",\"value\":\"attributeValue1\"}]}";


        Map<String, Object> params = new HashMap<>();
        params.put(EventParams.EXTERNAL_ID,INSTANCE_EXT_ID);
        params.put(EventParams.LOCATION,ORGUNIT_NAME);
        params.put(EventParams.DATE,DATE);
        params.put(EventParams.STAGE,STAGE_ID);
        params.put(EventParams.PROGRAM,PROGRAM_ID);
        params.put(testAttributeDto1.getDhis2Uuid(), testAttributeDto1.getValue());
        params.put(testAttributeDto2.getDhis2Uuid(), testAttributeDto2.getValue());
        params.put("nullValue", null);
        params.put(EventParams.REGISTRATION, "true");

        MotechEvent event = new MotechEvent(EventSubjects.UPDATE_PROGRAM_STAGE, params);

        StageDto stageDto = (StageDto) builder.createDto(event);

        assertNotNull(stageDto);
        assertEquals(stageDto.getTrackedEntityInstance(),INSTANCE_DHIS2_ID);
        assertEquals(stageDto.getDate(), DATE);
        assertEquals(stageDto.getProgram(), PROGRAM_ID);
        assertEquals(stageDto.getOrgUnit(), ORGUNIT_ID);
        assertEquals(stageDto.getDhis2Uuid(),STAGE_ID);
        assertEquals(stageDto.getTrackedEntityInstance(),INSTANCE_DHIS2_ID);

        List<AttributeDto> dataElements = stageDto.getDataElementDtos();
        assertNotNull(dataElements);
        assertTrue(dataElements.contains(testAttributeDto1));
        assertTrue(dataElements.contains(testAttributeDto2));
        assertEquals(2,dataElements.size());

        assertEquals(toJson,stageDto.toJson());

    }

    @Test
    public void testStageUpdateWithoutRegistration () throws Exception {

        String toJson = "{\"program\":\"programID\",\"orgUnit\":\"OrgUnitID\",\"eventDate\":\"date\"," +
                "\"programStage\":\"stageID\",\"dataValues\":" +
                "[{\"dataElement\":\"attributeUuid2\",\"value\":\"attributeValue2\"}," +
                "{\"dataElement\":\"attributeUuid1\",\"value\":\"attributeValue1\"}]}";


        Map<String, Object> params = new HashMap<>();
        params.put(EventParams.EXTERNAL_ID,INSTANCE_EXT_ID);
        params.put(EventParams.LOCATION,ORGUNIT_NAME);
        params.put(EventParams.DATE,DATE);
        params.put(EventParams.STAGE,STAGE_ID);
        params.put(EventParams.PROGRAM,PROGRAM_ID);
        params.put(testAttributeDto1.getDhis2Uuid(), testAttributeDto1.getValue());
        params.put(testAttributeDto2.getDhis2Uuid(), testAttributeDto2.getValue());
        params.put("nullValue", null);
        params.put(EventParams.REGISTRATION,"false");

        MotechEvent event = new MotechEvent(EventSubjects.UPDATE_PROGRAM_STAGE, params);

        StageDto stageDto = (StageDto) builder.createDto(event);

        assertNotNull(stageDto);
        assertNull(stageDto.getTrackedEntityInstance());
        assertEquals(stageDto.getDate(),DATE);
        assertEquals(stageDto.getProgram(),PROGRAM_ID);
        assertEquals(stageDto.getOrgUnit(),ORGUNIT_ID);
        assertEquals(stageDto.getDhis2Uuid(),STAGE_ID);


        List<AttributeDto> dataElements = stageDto.getDataElementDtos();
        assertNotNull(dataElements);
        assertTrue(dataElements.contains(testAttributeDto1));
        assertTrue(dataElements.contains(testAttributeDto2));
        assertEquals(2,dataElements.size());

        assertEquals(toJson,stageDto.toJson());

    }

}
