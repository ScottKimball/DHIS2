package org.motechproject.dhis2.event;

import org.motechproject.dhis2.rest.domain.AttributeDto;
import org.motechproject.dhis2.rest.domain.DataValueDto;
import org.motechproject.dhis2.rest.domain.DhisEventDto;
import org.motechproject.dhis2.rest.domain.EnrollmentDto;
import org.motechproject.dhis2.rest.domain.TrackedEntityInstanceDto;
import org.motechproject.dhis2.rest.service.DhisWebService;
import org.motechproject.dhis2.service.OrgUnitService;
import org.motechproject.dhis2.service.SettingsService;
import org.motechproject.dhis2.service.TrackedEntityInstanceMapperService;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class EventHandler {

    @Autowired
    @Qualifier("dhisSettingsService")
    private SettingsService settingsService;

    @Autowired
    private DhisWebService dhisWebService;

    @Autowired
    private TrackedEntityInstanceMapperService trackedEntityInstanceMapperService;

    @Autowired
    private OrgUnitService orgUnitService;

    @MotechListener(subjects = {EventSubjects.CREATE_ENTITY })
    public void handleRegistration(MotechEvent event) {
        TrackedEntityInstanceDto trackedEntityInstance = createTrackedEntityInstanceFromParams(event.getParameters());
        dhisWebService.createTrackedEntityInstance(trackedEntityInstance);

        // TODO: create a new instance in the mapper on success
    }

    @MotechListener(subjects = {EventSubjects.ENROLL_IN_PROGRAM })
    public void handleEnrollment(MotechEvent event) {
        EnrollmentDto enrollment = createEnrollmentFromParams(event.getParameters());
        dhisWebService.createEnrollment(enrollment);
    }


    @MotechListener(subjects = {EventSubjects.UPDATE_PROGRAM_STAGE })
    public void handleStageUpdate(MotechEvent event) {
        DhisEventDto dhisEventDto = createDhisEventFromParams(event.getParameters());
        dhisWebService.createEvent(dhisEventDto);
    }

    private TrackedEntityInstanceDto createTrackedEntityInstanceFromParams(Map<String, Object> params) {
        String externalUUID = (String) params.remove(EventParams.EXTERNAL_ID);
        String trackedEntity = (String) params.remove(EventParams.ENTITY_TYPE);

        /*Get org Unit*/
        String orgUnitName = (String) params.remove(EventParams.LOCATION);
        String orgUnitId = orgUnitService.findByName(orgUnitName).getUuid();

        List<AttributeDto> attributes = new ArrayList<AttributeDto>();
        for (Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                AttributeDto attribute = new AttributeDto();
                attribute.setAttribute(entry.getKey());
                attribute.setValue((String) entry.getValue());
                attributes.add(attribute);
            }
        }

        TrackedEntityInstanceDto trackedEntityInstance = new TrackedEntityInstanceDto();
        trackedEntityInstance.setTrackedEntity(trackedEntity);
        trackedEntityInstance.setOrgUnit(orgUnitId);
        trackedEntityInstance.setAttributes(attributes);

        return trackedEntityInstance;
    }

    private EnrollmentDto createEnrollmentFromParams(Map<String, Object> params) {
        String program = (String) params.remove(EventParams.PROGRAM);

        String externalId = (String) params.remove(EventParams.EXTERNAL_ID);
        String trackedEntityInstanceId = trackedEntityInstanceMapperService.getDhisId(externalId);

        String date = (String) params.remove(EventParams.DATE);

        List<AttributeDto> attributes = new ArrayList<>();
        for (Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                AttributeDto attribute = new AttributeDto();
                attribute.setAttribute(entry.getKey());
                attribute.setValue((String) entry.getValue());
                attributes.add(attribute);
            }
        }

        EnrollmentDto enrollment = new EnrollmentDto();
        enrollment.setProgram(program);
        enrollment.setTrackedEntityInstance(trackedEntityInstanceId);
        enrollment.setDateOfEnrollment(date);
        enrollment.setAttributes(attributes);

        return enrollment;
    }

    private DhisEventDto createDhisEventFromParams(Map<String, Object> params) {
        DhisEventDto dhisEventDto = new DhisEventDto();

        String registationString = (String) params.remove(EventParams.REGISTRATION);
        boolean registration = registationString.contains("true") ? true : false;

        String trackedEntityInstanceId = trackedEntityInstanceMapperService.getDhisId((String) params.remove(EventParams.EXTERNAL_ID));

        /*Get instance uuid*/
        if (registration) {
            dhisEventDto.setTrackedEntityInstance(trackedEntityInstanceId);
        }

        String orgUnitName = (String) params.remove(EventParams.LOCATION);
        String orgUnitId = orgUnitService.findByName(orgUnitName).getUuid();

        String program = (String) params.remove(EventParams.PROGRAM);

        String date = (String) params.remove(EventParams.DATE);

        /*Get stage UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String stage = (String) params.remove(EventParams.STAGE);

        List<DataValueDto> dataValues = new ArrayList<>();

        for (Entry<String, Object> entry : params.entrySet()) {
            DataValueDto dataValue = new DataValueDto();
            dataValue.setDataElement(entry.getKey());
            dataValue.setValue((String) entry.getValue());
            dataValues.add(dataValue);
        }

        dhisEventDto.setProgram(program);
        dhisEventDto.setEventDate(date);
        dhisEventDto.setProgramStage(stage);
        dhisEventDto.setOrgUnit(orgUnitId);
        dhisEventDto.setDataValues(dataValues);

        return dhisEventDto;
    }

    @MotechListener(subjects = {EventSubjects.CREATE_AND_ENROLL })
    public void handleCreateAndEnroll(MotechEvent event) {

        Map<String, Object> params = new HashMap<>();
        params.put(EventParams.PROGRAM, event.getParameters().remove(EventParams.PROGRAM));
        params.put(EventParams.DATE, event.getParameters().remove(EventParams.DATE));
        params.put(EventParams.EXTERNAL_ID, event.getParameters().get(EventParams.EXTERNAL_ID));

        TrackedEntityInstanceDto instance = createTrackedEntityInstanceFromParams(event.getParameters());
        dhisWebService.createTrackedEntityInstance(instance);

        MotechEvent newEvent = new MotechEvent(EventSubjects.ENROLL_IN_PROGRAM, params);
        EnrollmentDto enrollmentDto = createEnrollmentFromParams(event.getParameters());
        dhisWebService.createEnrollment(enrollmentDto);
    }
}
