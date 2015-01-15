package org.motechproject.dhis2.event;

import org.motechproject.dhis2.dto.builder.DtoBuilder;
import org.motechproject.dhis2.dto.DtoType;
import org.motechproject.dhis2.dto.impl.EnrollmentDto;
import org.motechproject.dhis2.dto.impl.StageDto;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.service.EnrollmentService;
import org.motechproject.dhis2.service.RegistrationService;
import org.motechproject.dhis2.service.StageService;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by scott on 8/26/14.
 */
@Component
public class EventHandler {


    private EnrollmentService enrollmentService;
    private StageService stageService;
    private RegistrationService registrationService;
    private DtoBuilder dtoBuilder;

    @Autowired
    public EventHandler(EnrollmentService enrollmentService, StageService stageService,
                        RegistrationService registrationService, DtoBuilder dtoBuilder) {
        this.enrollmentService = enrollmentService;
        this.stageService = stageService;
        this.registrationService = registrationService;
        this.dtoBuilder = dtoBuilder;
    }


    @MotechListener(subjects = {EventSubjects.REGISTER_ENTITY })
    public void handleRegistration(MotechEvent event) {
        TrackedEntityInstanceDto instance = (TrackedEntityInstanceDto) dtoBuilder.createDto(event, DtoType.REGISTRATION);
        registrationService.send(instance);

    }


    @MotechListener(subjects = {EventSubjects.ENROLL_IN_PROGRAM })
    public void handleEnrollment(MotechEvent event) {
        EnrollmentDto enrollmentDto = (EnrollmentDto) dtoBuilder.createDto(event, DtoType.ENROLLMENT);
        enrollmentService.send(enrollmentDto);

    }


    @MotechListener(subjects = {EventSubjects.UPDATE_PROGRAM_STAGE })
    public void handleStageUpdate(MotechEvent event) {
        StageDto stageDto = (StageDto) dtoBuilder.createDto(event, DtoType.STAGE_UPDATE);
        stageService.send(stageDto);

    }


}
