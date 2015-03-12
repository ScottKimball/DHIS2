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
import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.domain.Settings;
import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.rest.domain.AttributeDto;
import org.motechproject.dhis2.rest.domain.DhisStatusResponse;
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




    @Before
    public void setup() throws Exception{



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

        when(dhisWebservice.createTrackedEntityInstance(argThat(new InstanceMatcher(instance)))).thenReturn(response);

        Map<String,Object> params = new HashMap<>();
        params.put(EventParams.EXTERNAL_ID,ENTITY_INSTANCE_ID);
        params.put(EventParams.ENTITY_TYPE, ENTITY_TYPE_PERSON );
        params.put(EventParams.LOCATION,ORGUNIT_NAME);
        params.put(ATTRIBUTE_ID, ATTRIBUTE_VALUE);

        MotechEvent event = new MotechEvent(EventSubjects.CREATE_ENTITY,params);

        handler.handleCreate(event);
        verify(trackedEntityInstanceMapperService).create(ENTITY_INSTANCE_ID,INSTANCE_DHIS_ID);
        verify(dhisWebservice).createTrackedEntityInstance(argThat(new InstanceMatcher(instance)));

    }

    private class InstanceMatcher extends ArgumentMatcher<TrackedEntityInstanceDto> {

        private TrackedEntityInstanceDto dto;

        public InstanceMatcher(TrackedEntityInstanceDto dto) {
            this.dto = dto;
        }

        @Override
        public boolean matches(Object o) {
            TrackedEntityInstanceDto other = (TrackedEntityInstanceDto) o;
            if (other.getTrackedEntityInstance() != null && dto.getTrackedEntityInstance() != null)
                return other.getTrackedEntityInstance().equals(dto.getTrackedEntityInstance());


            return other.getTrackedEntity().equals(dto.getTrackedEntity())
                    && other.getOrgUnit().equals(dto.getOrgUnit())
                    && other.getAttributes().size() == dto.getAttributes().size();
        }
    }



}
