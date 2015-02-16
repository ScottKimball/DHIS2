package org.motechproject.dhis2.event;

import org.motechproject.dhis2.dto.builder.DtoBuilder;
import org.motechproject.dhis2.dto.impl.EnrollmentDto;
import org.motechproject.dhis2.dto.impl.StageDto;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.service.DataTransferService;
import org.motechproject.dhis2.service.InstanceCreationService;
import org.motechproject.dhis2.service.SettingsService;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventHandler {


    private InstanceCreationService instanceCreationService;
    private DtoBuilder dtoBuilder;
    private DataTransferService dataTransferService;
    private SettingsService settingsService;

    @Autowired
    public EventHandler(InstanceCreationService instanceCreationService,
                        DtoBuilder dtoBuilder,
                        DataTransferService dataTransferService,
                        @Qualifier("dhisSettingsService") SettingsService settingsService) {
        this.instanceCreationService = instanceCreationService;
        this.dtoBuilder = dtoBuilder;
        this.dataTransferService = dataTransferService;
        this.settingsService = settingsService;
    }


    @MotechListener(subjects = {EventSubjects.CREATE_ENTITY })
    public void handleRegistration(MotechEvent event) {
        TrackedEntityInstanceDto instance = (TrackedEntityInstanceDto) dtoBuilder.createDto(event);
        instanceCreationService.send(instance, settingsService.getSettings().getTrackedEntityInstancesURI());
    }


    @MotechListener(subjects = {EventSubjects.ENROLL_IN_PROGRAM })
    public void handleEnrollment(MotechEvent event) {
        EnrollmentDto enrollmentDto = (EnrollmentDto) dtoBuilder.createDto(event);
        dataTransferService.send(enrollmentDto, settingsService.getSettings().getEnrollmentsURI());
    }


    @MotechListener(subjects = {EventSubjects.UPDATE_PROGRAM_STAGE })
    public void handleStageUpdate(MotechEvent event) {
        StageDto stageDto = (StageDto) dtoBuilder.createDto(event);
        dataTransferService.send(stageDto, settingsService.getSettings().getEventsURI());
    }

    @MotechListener(subjects = {EventSubjects.CREATE_AND_ENROLL })
    public void handleCreateAndEnroll(MotechEvent event) {

        Map<String, Object> params = new HashMap<>();
        params.put(EventParams.PROGRAM, event.getParameters().remove(EventParams.PROGRAM));
        params.put(EventParams.DATE, event.getParameters().remove(EventParams.DATE));
        params.put(EventParams.EXTERNAL_ID, event.getParameters().get(EventParams.EXTERNAL_ID));


        TrackedEntityInstanceDto instance = (TrackedEntityInstanceDto) dtoBuilder.createDto(event);
        instanceCreationService.send(instance, settingsService.getSettings().getTrackedEntityInstancesURI());

        MotechEvent newEvent = new MotechEvent(EventSubjects.ENROLL_IN_PROGRAM, params);
        EnrollmentDto enrollmentDto = (EnrollmentDto) dtoBuilder.createDto(newEvent);
        dataTransferService.send(enrollmentDto, settingsService.getSettings().getEnrollmentsURI());


    }


}
