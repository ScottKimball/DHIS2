package org.motechproject.dhis2.dto.builder;

import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.dto.Dto;
import org.motechproject.dhis2.dto.impl.AttributeDto;
import org.motechproject.dhis2.dto.impl.EnrollmentDto;
import org.motechproject.dhis2.dto.impl.StageDto;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.event.EventSubjects;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.TrackedEntityInstanceDataService;
import org.motechproject.event.MotechEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by scott on 12/2/14.
 */
@Component
@Scope(value = "singleton")
public class DtoBuilder {


    private TrackedEntityInstanceDataService trackedEntityInstanceDataService;
    private OrgUnitDataService orgUnitDataService;

    private Logger logger = LoggerFactory.getLogger(DtoBuilder.class);

    @Autowired
    public DtoBuilder(TrackedEntityInstanceDataService trackedEntityInstanceDataService, OrgUnitDataService orgUnitDataService) {
        this.trackedEntityInstanceDataService = trackedEntityInstanceDataService;
        this.orgUnitDataService = orgUnitDataService;
    }

    public Dto createDto(MotechEvent event) {

        switch (event.getSubject()) {
            case EventSubjects.ENROLL_IN_PROGRAM:
                return createEnrollment(event);

            case EventSubjects.CREATE_ENTITY:
                return createInstance(event);

            case EventSubjects.UPDATE_PROGRAM_STAGE:
                return createStage(event);

            default:
                break;
        }

        return null;
    }

    private EnrollmentDto createEnrollment(MotechEvent event) {

        Map<String, Object> params = event.getParameters();

        String program = (String) params.remove(EventParams.PROGRAM);

        String externalId = (String) params.remove(EventParams.EXTERNAL_ID);
        TrackedEntityInstanceMapper instanceMapper = trackedEntityInstanceDataService.findByExternalName(externalId);

        String date = (String) params.remove(EventParams.DATE);

        List<AttributeDto> attributeDtoList = new ArrayList<>();
        addRemainingItemsToDto(params, attributeDtoList);

        return new EnrollmentDto(program, instanceMapper.getDhis2Uuid(), date, attributeDtoList);
    }

    private StageDto createStage(MotechEvent event) {

        StageDto stageDto = new StageDto();
        Map<String, Object> params = event.getParameters();

        String registationString = (String) params.remove(EventParams.REGISTRATION);
        boolean registration = registationString.contains("true");

        TrackedEntityInstanceMapper instanceMapper = trackedEntityInstanceDataService.
                findByExternalName((String) params.remove(EventParams.EXTERNAL_ID));

        /*Get instance uuid*/
        if (registration) {

            stageDto.setTrackedEntityInstance(instanceMapper.getDhis2Uuid());

        }

        String orgUnit = (String) params.remove(EventParams.LOCATION);
        String orgUnitUuid = getOrgUnitUuid(orgUnit);


        String program = (String) params.remove(EventParams.PROGRAM);

        String date = (String) params.remove(EventParams.DATE);

        /*Get stage UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String stage = (String) params.remove(EventParams.STAGE);


        List<AttributeDto> stageDataElementDtos = new ArrayList<>();

         addRemainingItemsToDto(params, stageDataElementDtos);


        stageDto.setProgram(program);
        stageDto.setDhis2Uuid(stage);
        stageDto.setDate(date);
        stageDto.setOrgUnit(orgUnitUuid);
        stageDto.setDataElementDtos(stageDataElementDtos);
        stageDto.setRegistration(registration);


        return stageDto;
    }

    private TrackedEntityInstanceDto createInstance(MotechEvent event) {

        Map<String, Object> params = event.getParameters();
        List<AttributeDto> attributeDtoList = new ArrayList<AttributeDto>();

        String externalUUID = (String) params.remove(EventParams.EXTERNAL_ID);
        String trackedEntity = (String) params.remove(EventParams.ENTITY_TYPE);

        /*Get org Unit*/
        String orgUnit = (String) params.remove(EventParams.LOCATION);
        String orgUnitUuid = getOrgUnitUuid(orgUnit);


        /*Iterate over remaining parameters*/
        addRemainingItemsToDto(params, attributeDtoList);

        return new TrackedEntityInstanceDto(externalUUID, trackedEntity, attributeDtoList, orgUnitUuid);
    }

    private String getOrgUnitUuid(String orgUnit) {

        try {

            return orgUnitDataService.findByName(orgUnit).getUuid();

        } catch (NullPointerException e) {
            logger.error("Org Unit not found: " + orgUnit);
            return null;
        }
    }

    private void addRemainingItemsToDto(Map<String, Object> params, List<AttributeDto> list) {

        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();

            if (entry.getValue() != null) {
                list.add(new AttributeDto(null, (String) entry.getKey(), (String) entry.getValue()));
            }

        }

    }


}
