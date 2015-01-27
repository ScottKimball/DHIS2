package org.motechproject.dhis2.event;

import org.motechproject.dhis2.dto.builder.DtoBuilder;
import org.motechproject.dhis2.dto.impl.EnrollmentDto;
import org.motechproject.dhis2.dto.impl.StageDto;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.service.DataTransferService;
import org.motechproject.dhis2.service.InstanceCreationService;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.motechproject.dhis2.http.HttpConstants.ENROLLMENTS_PATH;
import static org.motechproject.dhis2.http.HttpConstants.EVENTS_PATH;

/**
 * Created by scott on 8/26/14.
 */
@Component
public class EventHandler {


    private InstanceCreationService instanceCreationService;
    private DtoBuilder dtoBuilder;
    private DataTransferService dataTransferService;

    @Autowired
    public EventHandler(InstanceCreationService instanceCreationService, DtoBuilder dtoBuilder,
                         DataTransferService dataTransferService) {
        this.instanceCreationService = instanceCreationService;
        this.dtoBuilder = dtoBuilder;
        this.dataTransferService = dataTransferService;
    }


    @MotechListener(subjects = {EventSubjects.REGISTER_ENTITY })
    public void handleRegistration(MotechEvent event) {
        TrackedEntityInstanceDto instance = (TrackedEntityInstanceDto) dtoBuilder.createDto(event);
        instanceCreationService.send(instance);

    }


    @MotechListener(subjects = {EventSubjects.ENROLL_IN_PROGRAM })
    public void handleEnrollment(MotechEvent event) {
        EnrollmentDto enrollmentDto = (EnrollmentDto) dtoBuilder.createDto(event);
        dataTransferService.send(enrollmentDto, ENROLLMENTS_PATH);

    }


    @MotechListener(subjects = {EventSubjects.UPDATE_PROGRAM_STAGE })
    public void handleStageUpdate(MotechEvent event) {
        StageDto stageDto = (StageDto) dtoBuilder.createDto(event);
        dataTransferService.send(stageDto, EVENTS_PATH);

    }


}
