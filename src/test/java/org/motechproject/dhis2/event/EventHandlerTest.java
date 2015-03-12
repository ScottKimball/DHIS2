package org.motechproject.dhis2.event;

/**
 * Created by scott on 3/11/15.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.domain.Settings;
import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.rest.domain.AttributeDto;
import org.motechproject.dhis2.rest.domain.DataValueDto;
import org.motechproject.dhis2.rest.domain.DhisEventDto;
import org.motechproject.dhis2.rest.domain.DhisStatusResponse;
import org.motechproject.dhis2.rest.domain.EnrollmentDto;
import org.motechproject.dhis2.rest.domain.TrackedEntityInstanceDto;
import org.motechproject.dhis2.rest.service.DhisWebService;
import org.motechproject.dhis2.rest.service.impl.DhisWebServiceImpl;
import org.motechproject.dhis2.service.OrgUnitService;
import org.motechproject.dhis2.service.SettingsService;
import org.motechproject.dhis2.service.TrackedEntityInstanceMapperService;
import org.motechproject.event.MotechEvent;
import org.motechproject.testing.osgi.http.SimpleHttpServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreators;
import org.springframework.test.web.server.result.JsonPathResultMatchers;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.client.RequestMatchers.body;
import static org.springframework.test.web.client.RequestMatchers.method;
import static org.springframework.test.web.client.RequestMatchers.requestTo;
import static org.springframework.test.web.client.ResponseCreators.withSuccess;



@RunWith(MockitoJUnitRunner.class)
public class EventHandlerTest {

    private static final String ORGUNIT_NAME = "orgUnitName";
    private static final String ORGUNIT_ID = "orgUnitID";
    private static final String ENTITY_TYPE_PERSON = "person";
    private static final String ENTITY_INSTANCE_ID = "externalID";
    private static final String ATTRIBUTE_VALUE = "attributeValue";
    private static final String ATTRIBUTE_ID = "attributeID";
    private static final String INSTANCE_DHIS_ID = "dhis2uuid";
    private static final String PROGRAM_ID = "programID";
    private static final String DATE = "2014-01-01";
    private static final String REGISTRATION = "true";
    private static final String STAGE_ID = "stageId";
    private static final String DATA_ELEMENT_ID = "dataElementID";
    private static final String DATA_ELEMENT_VALUE = "value";



    private EventHandler handler;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    SettingsService settingsService;
    @Mock
    private TrackedEntityInstanceMapperService trackedEntityInstanceMapperService;
    @Mock
    private OrgUnitService orgUnitService;
    @Mock
    private DhisWebService dhisWebservice;

    private DhisStatusResponse response;


    @Before
    public void setup() throws Exception{

        DhisStatusResponse.ImportCount importCount = new DhisStatusResponse.ImportCount();
        importCount.setImported(1);
        importCount.setUpdated(0);
        importCount.setIgnored(0);
        importCount.setDeleted(0);

        response = new DhisStatusResponse();
        response.setReference(INSTANCE_DHIS_ID);
        response.setStatus(DhisStatusResponse.DhisStatus.SUCCESS);
        response.setImportCount(importCount);



        when(orgUnitService.findByName(ORGUNIT_NAME)).thenReturn(new OrgUnit(ORGUNIT_NAME, ORGUNIT_ID));
        handler = new EventHandler(dhisWebservice,trackedEntityInstanceMapperService,orgUnitService);

    }

    @Test
    public void testCreate () throws Exception {

        List<AttributeDto> attributeDtos = new ArrayList<>();
        AttributeDto dto = new AttributeDto();
        dto.setAttribute(ATTRIBUTE_ID);
        dto.setValue(ATTRIBUTE_VALUE);
        attributeDtos.add(dto);

        TrackedEntityInstanceDto instance = new TrackedEntityInstanceDto();
        instance.setTrackedEntity(ENTITY_TYPE_PERSON);
        instance.setAttributes(attributeDtos);
        instance.setOrgUnit(ORGUNIT_ID);

        DhisStatusResponse.ImportCount importCount = new DhisStatusResponse.ImportCount();
        importCount.setImported(1);
        importCount.setUpdated(0);
        importCount.setIgnored(0);
        importCount.setDeleted(0);

        DhisStatusResponse response = new DhisStatusResponse();
        response.setReference(INSTANCE_DHIS_ID);
        response.setStatus(DhisStatusResponse.DhisStatus.SUCCESS);
        response.setImportCount(importCount);

        String dhisResponse = objectMapper.writeValueAsString(response);

        when(dhisWebservice.createTrackedEntityInstance(instance)).thenReturn(response);

        Map<String,Object> params = new HashMap<>();
        params.put(EventParams.EXTERNAL_ID,ENTITY_INSTANCE_ID);
        params.put(EventParams.ENTITY_TYPE, ENTITY_TYPE_PERSON );
        params.put(EventParams.LOCATION,ORGUNIT_NAME);
        params.put(ATTRIBUTE_ID, ATTRIBUTE_VALUE);

        MotechEvent event = new MotechEvent(EventSubjects.CREATE_ENTITY,params);

        handler.handleCreate(event);
        verify(trackedEntityInstanceMapperService).create(ENTITY_INSTANCE_ID,INSTANCE_DHIS_ID);
        verify(dhisWebservice).createTrackedEntityInstance(instance);

    }


    @Test
    public void testEnrollment() throws Exception {

        List<AttributeDto> attributeDtos = new ArrayList<>();
        AttributeDto dto = new AttributeDto();
        dto.setAttribute(ATTRIBUTE_ID);
        dto.setValue(ATTRIBUTE_VALUE);
        attributeDtos.add(dto);

        EnrollmentDto enrollment = new EnrollmentDto();
        enrollment.setAttributes(attributeDtos);
        enrollment.setDateOfEnrollment(DATE);
        enrollment.setProgram(PROGRAM_ID);
        enrollment.setTrackedEntityInstance(INSTANCE_DHIS_ID);

        when(trackedEntityInstanceMapperService.mapFromExternalId(ENTITY_INSTANCE_ID))
                .thenReturn(INSTANCE_DHIS_ID);

        when(dhisWebservice.createEnrollment(enrollment)).thenReturn(response);

        Map<String,Object> params = new HashMap<>();
        params.put(EventParams.PROGRAM,PROGRAM_ID );
        params.put(EventParams.EXTERNAL_ID, ENTITY_INSTANCE_ID);
        params.put(EventParams.DATE, DATE);
        params.put(ATTRIBUTE_ID, ATTRIBUTE_VALUE);

        MotechEvent event = new MotechEvent(EventSubjects.ENROLL_IN_PROGRAM,params);

        handler.handleEnrollment(event);
        verify(trackedEntityInstanceMapperService).mapFromExternalId(ENTITY_INSTANCE_ID);
        verify(dhisWebservice).createEnrollment(enrollment);

    }

    @Test
    public void testEventWithRegistration() throws Exception {

        List<DataValueDto> dataValues = new ArrayList<>();
        DataValueDto datavalue = new DataValueDto();
        datavalue.setDataElement(DATA_ELEMENT_ID);
        datavalue.setValue(DATA_ELEMENT_VALUE);
        dataValues.add(datavalue);

        DhisEventDto programStageDto = new DhisEventDto();
        programStageDto.setDataValues(dataValues);
        programStageDto.setTrackedEntityInstance(INSTANCE_DHIS_ID);
        programStageDto.setProgram(PROGRAM_ID);
        programStageDto.setEventDate(DATE);
        programStageDto.setOrgUnit(ORGUNIT_ID);
        programStageDto.setProgramStage(STAGE_ID);

        when(dhisWebservice.createEvent(programStageDto)).thenReturn(response);
        when(trackedEntityInstanceMapperService.mapFromExternalId(ENTITY_INSTANCE_ID))
                .thenReturn(INSTANCE_DHIS_ID);

        Map<String, Object> params = new HashMap<>();
        params.put(EventParams.REGISTRATION, REGISTRATION);
        params.put(EventParams.EXTERNAL_ID, ENTITY_INSTANCE_ID);
        params.put(EventParams.LOCATION, ORGUNIT_NAME);
        params.put(EventParams.PROGRAM, PROGRAM_ID);
        params.put(EventParams.DATE, DATE);
        params.put(EventParams.STAGE,STAGE_ID);

        MotechEvent event = new MotechEvent(EventSubjects.UPDATE_PROGRAM_STAGE, params);
        handler.handleStageUpdate(event);

        verify(trackedEntityInstanceMapperService).mapFromExternalId(ENTITY_INSTANCE_ID);
        verify(dhisWebservice).createEvent(programStageDto);

    }


}
