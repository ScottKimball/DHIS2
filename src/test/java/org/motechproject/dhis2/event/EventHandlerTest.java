package org.motechproject.dhis2.event;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.dhis2.dto.builder.DtoBuilder;
import org.motechproject.dhis2.dto.impl.EnrollmentDto;
import org.motechproject.dhis2.dto.impl.StageDto;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.http.HttpService;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.dhis2.service.DataTransferService;
import org.motechproject.dhis2.service.InstanceCreationService;
import org.motechproject.dhis2.service.SettingsService;
import org.motechproject.dhis2.service.impl.InstanceCreationServiceImpl;
import org.motechproject.event.MotechEvent;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by scottkimball on 1/27/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class EventHandlerTest {

    private EventHandler handler;

    @Mock
    private DtoBuilder builder;

    private InstanceCreationService instanceCreationService;

    private DataTransferService dataTransferService;

    private MotechEvent instanceEvent;
    private MotechEvent enrollmentEvent;
    private MotechEvent stageEvent;


    @Mock
    private TrackedEntityInstanceDto instanceDto;

    @Mock
    private EnrollmentDto enrollmentDto;

    @Mock
    private StageDto stageDto;

    @Mock
    HttpService httpService;

    @Mock
    TrackedEntityInstanceDataService instanceDataService;

    @Mock
    SettingsService settingsService;





    @Before
    public void setUp() {


        instanceCreationService = new InstanceCreationServiceImpl(httpService,instanceDataService, settingsService);
        handler = new EventHandler(instanceCreationService,builder,dataTransferService,settingsService);

        try {
            when(instanceDto.toJson()).thenReturn("toJson");
            when(enrollmentDto.toJson()).thenReturn("toJson");
            when(stageDto.toJson()).thenReturn("toJson");
        } catch (IOException e) {

        }


        when(builder.createDto(instanceEvent)).thenReturn(instanceDto);
    }

    @Test
    public void testReceiveInstanceCreationEvent() throws Exception {
        instanceEvent = new MotechEvent(EventSubjects.CREATE_ENTITY);

    }
}
