package org.motechproject.dhis2.event;

import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.rest.domain.AttributeDto;
import org.motechproject.dhis2.rest.domain.DataValueDto;
import org.motechproject.dhis2.rest.domain.DataValueSetDto;
import org.motechproject.dhis2.rest.domain.DhisEventDto;
import org.motechproject.dhis2.rest.domain.DhisStatusResponse;
import org.motechproject.dhis2.rest.domain.DhisStatus;
import org.motechproject.dhis2.rest.domain.EnrollmentDto;
import org.motechproject.dhis2.rest.domain.TrackedEntityInstanceDto;
import org.motechproject.dhis2.rest.service.DhisWebService;
import org.motechproject.dhis2.service.DataElementService;
import org.motechproject.dhis2.service.OrgUnitService;
import org.motechproject.dhis2.service.TrackedEntityInstanceMapperService;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Event Handler for the DHIS2 module. The public methods listen for the event subjects listed in
 * {@link org.motechproject.dhis2.event.EventSubjects}
 *
 */
@Service
public class EventHandler {
    @Autowired
    private DhisWebService dhisWebService;

    @Autowired
    private TrackedEntityInstanceMapperService trackedEntityInstanceMapperService;

    @Autowired
    private OrgUnitService orgUnitService;

    @Autowired
    private DataElementService dataElementService;

    public EventHandler(DhisWebService webService,
                        TrackedEntityInstanceMapperService trackedEntityInstanceMapperService,
                        OrgUnitService orgUnitService) {

        this.dhisWebService = webService;
        this.trackedEntityInstanceMapperService = trackedEntityInstanceMapperService;
        this.orgUnitService = orgUnitService;

    }


    public EventHandler() {}

    /**
     * Parses the MotechEvent and creates a {@link org.motechproject.dhis2.rest.domain.TrackedEntityInstanceDto}
     * which is then sent to the DHIS2 server via {@link org.motechproject.dhis2.rest.service.DhisWebService}
     *
     * @param event MotechEvent pertaining to tracked entity instance creation.
     */
    @MotechListener(subjects = {EventSubjects.CREATE_ENTITY })
    public void handleCreate(MotechEvent event) {
        Map<String, Object> params = new HashMap<String, Object>(event.getParameters());
        String externalUUID = (String) params.remove(EventParams.EXTERNAL_ID);
        TrackedEntityInstanceDto trackedEntityInstance = createTrackedEntityInstanceFromParams(params);
        DhisStatusResponse response = dhisWebService.createTrackedEntityInstance(trackedEntityInstance);

        if (response.getStatus() == DhisStatus.SUCCESS) {
            trackedEntityInstanceMapperService.create(externalUUID, response.getReference());
        }
    }

    /**
     * Parses the MotechEvent and creates a {@link org.motechproject.dhis2.rest.domain.EnrollmentDto}
     * which is then sent to the DHIS2 server via {@link org.motechproject.dhis2.rest.service.DhisWebService}
     *
     * @param event MotechEvent pertaining to enrolling a tracked entity instance in a program.
     */
    @MotechListener(subjects = {EventSubjects.ENROLL_IN_PROGRAM })
    public void handleEnrollment(MotechEvent event) {
        Map<String, Object> params = new HashMap<String, Object>(event.getParameters());
        EnrollmentDto enrollment = createEnrollmentFromParams(params);
        dhisWebService.createEnrollment(enrollment);
    }


    /**
     * Parses the MotechEvent and creates a {@link org.motechproject.dhis2.rest.domain.DhisEventDto}
     * which is then sent to the DHIS2 server via {@link org.motechproject.dhis2.rest.service.DhisWebService}
     *
     * @param event MotechEvent pertaining to a DHIS2 program stage event.
     */
    @MotechListener(subjects = {EventSubjects.UPDATE_PROGRAM_STAGE })
    public void handleStageUpdate(MotechEvent event) {
        Map<String, Object> params = new HashMap<String, Object>(event.getParameters());
        DhisEventDto dhisEventDto = createDhisEventFromParams(params);
        dhisWebService.createEvent(dhisEventDto);
    }

    /**
     * Parses the event and creates a{@link org.motechproject.dhis2.rest.domain.TrackedEntityInstanceDto}
     * and a {@link org.motechproject.dhis2.rest.domain.EnrollmentDto} which is then sent to the DHIS2 server
     * via {@link org.motechproject.dhis2.rest.service.DhisWebService}
     *
     * @param event
     */
    @MotechListener(subjects = {EventSubjects.CREATE_AND_ENROLL })
    public void handleCreateAndEnroll(MotechEvent event) {
        Map<String, Object> params = new HashMap<String, Object>(event.getParameters());
        Map<String, Object> enrollmentParams = new HashMap<String, Object>();

        enrollmentParams.put(EventParams.PROGRAM, params.remove(EventParams.PROGRAM));
        enrollmentParams.put(EventParams.DATE, params.remove(EventParams.DATE));
        enrollmentParams.put(EventParams.EXTERNAL_ID, params.get(EventParams.EXTERNAL_ID));

        handleCreate(new MotechEvent(EventSubjects.CREATE_ENTITY, params));
        handleEnrollment(new MotechEvent(EventSubjects.ENROLL_IN_PROGRAM, enrollmentParams));
    }


    /**
     * Parses the event and creates a{@link org.motechproject.dhis2.rest.domain.DataValueDto}which
     * is then sent to the DHIS2 server via {@link org.motechproject.dhis2.rest.service.DhisWebService}
     *
     * @param event
     */
    @MotechListener(subjects = EventSubjects.SEND_DATA_VALUE)
    public void handleDataValue (MotechEvent event) {

        Map<String, Object> params = event.getParameters();

        DataElement dataElement = dataElementService.findByName((String) params.get(EventParams.DATA_ELEMENT));
        OrgUnit orgUnit = orgUnitService.findByName((String)params.get(EventParams.LOCATION));
        String period = (String) params.get(EventParams.PERIOD);
        String value = (String) params.get(EventParams.VALUE);
        String categoryOptionCombo = (String) params.get(EventParams.CATEGORY_OPTION_COMBO);
        String comment = (String)params.get(EventParams.COMMENT);

        DataValueDto dataValueDto = new DataValueDto();
        dataValueDto.setDataElement(dataElement.getUuid());
        dataValueDto.setValue(value);
        dataValueDto.setOrgUnit(orgUnit.getUuid());
        dataValueDto.setPeriod(period);
        dataValueDto.setCategoryOptionCombo(categoryOptionCombo);
        dataValueDto.setComment(comment);

        DataValueSetDto dataValueSetDto = new DataValueSetDto();
        List<DataValueDto> dataValueDtos = new ArrayList<>();
        dataValueDtos.add(dataValueDto);
        dataValueSetDto.setDataValues(dataValueDtos);

        dhisWebService.sendDataValueSet(dataValueSetDto);

    }

    /**
     * Parses the event and creates a{@link org.motechproject.dhis2.rest.domain.DataValueSetDto}which
     * is then sent to the DHIS2 server via {@link org.motechproject.dhis2.rest.service.DhisWebService}
     *
     * @param event
     */
    @MotechListener(subjects = EventSubjects.SEND_DATA_VALUE_SET)
    public void handleDataValueSet (MotechEvent event) {
        Map<String, Object> params = event.getParameters();
        String dataSet = (String) params.get(EventParams.DATA_SET);
        String completeDate = (String) params.get(EventParams.COMPLETE_DATE);
        String period = (String) params.get(EventParams.PERIOD);
        String orgUnit = (String) params.get(EventParams.LOCATION);
        String categoryOptionCombo = (String) params.get(EventParams.CATEGORY_OPTION_COMBO);
        String comment = (String) params.get(EventParams.COMMENT);
        String attributeOptionCombo = (String) params.get(EventParams.ATTRIBUTE_OPTION_COMBO);
        Map<String, Object> dataValues = (Map<String, Object>) params.get(EventParams.DATA_VALUES);

        String orgUnitID = orgUnitService.findByName(orgUnit).getUuid();

        List<DataValueDto> dataValueDtos = new ArrayList<>();

        for (Object o : dataValues.entrySet()) {
            Entry pair = (Entry) o;
            String dataElement = (String) pair.getKey();
            String dataElementId = dataElementService.findByName(dataElement).getUuid();
            String value = (String) pair.getValue();
            DataValueDto dataValueDto = new DataValueDto();
            dataValueDto.setDataElement(dataElementId);
            dataValueDto.setValue(value);

            dataValueDtos.add(dataValueDto);
        }

        DataValueSetDto dataValueSetDto = new DataValueSetDto();
        dataValueSetDto.setDataSet(dataSet);
        dataValueSetDto.setPeriod(period);
        dataValueSetDto.setCompleteDate(completeDate);
        dataValueSetDto.setOrgUnit(orgUnitID);
        dataValueSetDto.setDataValues(dataValueDtos);
        dataValueSetDto.setAttributeOptionCombo(attributeOptionCombo);
        dataValueSetDto.setCategoryOptionCombo(categoryOptionCombo);
        dataValueSetDto.setComment(comment);
        dhisWebService.sendDataValueSet(dataValueSetDto);

    }

    private TrackedEntityInstanceDto createTrackedEntityInstanceFromParams(Map<String, Object> params) {
        String trackedEntity = (String) params.remove(EventParams.ENTITY_TYPE);
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
        String trackedEntityInstanceId = trackedEntityInstanceMapperService.mapFromExternalId(externalId);

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
        boolean registration = registationString.contains("true");

        String trackedEntityInstanceId = trackedEntityInstanceMapperService.mapFromExternalId((String) params.remove(EventParams.EXTERNAL_ID));

        if (registration) {
            dhisEventDto.setTrackedEntityInstance(trackedEntityInstanceId);
        }

        String orgUnitName = (String) params.remove(EventParams.LOCATION);
        String orgUnitId = orgUnitService.findByName(orgUnitName).getUuid();
        String program = (String) params.remove(EventParams.PROGRAM);
        String date = (String) params.remove(EventParams.DATE);
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
}
