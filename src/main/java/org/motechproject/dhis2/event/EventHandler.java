package org.motechproject.dhis2.event;

import com.jayway.jsonpath.JsonPath;
import org.motechproject.dhis2.domain.*;
import org.motechproject.dhis2.dto.Attribute;
import org.motechproject.dhis2.dto.Dto;
import org.motechproject.dhis2.dto.DtoBuilder;
import org.motechproject.dhis2.dto.DtoType;
import org.motechproject.dhis2.dto.impl.Enrollment;
import org.motechproject.dhis2.dto.impl.Stage;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstance;
import org.motechproject.dhis2.http.HttpConstants;
import org.motechproject.dhis2.http.HttpQuery;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.repository.*;
import org.motechproject.dhis2.service.EnrollmentService;
import org.motechproject.dhis2.service.RegistrationService;
import org.motechproject.dhis2.service.StageService;
import org.motechproject.event.listener.EventRelay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.springframework.stereotype.Component;


import java.util.*;


/**
 * Created by scott on 8/26/14.
 */
@Component
public class EventHandler {

    /*HardWired UUID values (Not needed currently but keeping them for reference right now)
    private String trackedEntityUUID = "cyl5vuJ5ETQ"; // uuid for person tracked entity
    private String programUUID = "ur1Edk5Oe2n";
    private String orgUnitUUID = "g8upMTyEZGZ";
    private String lastNameUUID = "hwlRTFIFSUq";
    private String firstNameUUID = "dv3nChNSIxy";
    private String genderUUID = "cejWyOfXge6";
    private String nationalIdentifierUUID = "AuPLng5hLbE";
    private String stage_uuid = "vxQUcroMY0r";
    */

    private Logger logger = LoggerFactory.getLogger(EventHandler.class);
    private EventRelay eventRelay;
    private EnrollmentService enrollmentService;
    private StageService stageService;
    private RegistrationService registrationService;
    private HttpQuery httpQuery;
    private DtoBuilder dtoBuilder;

    @Autowired
    public EventHandler(EventRelay eventRelay, EnrollmentService enrollmentService, StageService stageService,
                        RegistrationService registrationService, HttpQuery httpQuery, DtoBuilder dtoBuilder) {
        this.eventRelay = eventRelay;
        this.enrollmentService = enrollmentService;
        this.stageService = stageService;
        this.registrationService = registrationService;
        this.httpQuery = httpQuery;
        this.dtoBuilder = dtoBuilder;
    }

    /*This will be the generic entity registration event handler*/
    @MotechListener(subjects = {EventSubjects.REGISTER_ENTITY})
    public void handleRegistration(MotechEvent event) {
        TrackedEntityInstance instance =(TrackedEntityInstance) dtoBuilder.createDto(event, DtoType.REGISTRATION);
        registrationService.send(instance);

    }

    /*This will be the generic program enrollment event handler */
    @MotechListener(subjects = {EventSubjects.ENROLL_IN_PROGRAM})
    public void handleEnrollment(MotechEvent event) {
        Enrollment enrollment =(Enrollment) dtoBuilder.createDto(event,DtoType.ENROLLMENT);
        enrollmentService.send(enrollment);

    }

    /*This will be the generic program stage event handler */
    @MotechListener(subjects = {EventSubjects.UPDATE_PROGRAM_STAGE})
    public void handleStageUpdate(MotechEvent event) {
        Stage stage = (Stage) dtoBuilder.createDto(event,DtoType.STAGE_UPDATE);
        stageService.send(stage);

    }

   

}
