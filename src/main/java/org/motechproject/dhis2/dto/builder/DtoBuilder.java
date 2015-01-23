package org.motechproject.dhis2.dto.builder;

import org.motechproject.dhis2.domain.TrackedEntityInstanceMapper;
import org.motechproject.dhis2.dto.Dto;
import org.motechproject.dhis2.dto.DtoType;
import org.motechproject.dhis2.dto.impl.AttributeDto;
import org.motechproject.dhis2.dto.impl.EnrollmentDto;
import org.motechproject.dhis2.dto.impl.StageDto;
import org.motechproject.dhis2.dto.impl.TrackedEntityInstanceDto;
import org.motechproject.dhis2.event.EventParams;
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

    public Dto createDto(MotechEvent event, DtoType dtoType) {

        switch (dtoType) {
            case ENROLLMENT:
                return createEnrollment(event);

            case INSTANCE_CREATION:
                return createInstance(event);

            case EVENT:
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
        addRemainderToDto(params, attributeDtoList);

        return new EnrollmentDto(program, instanceMapper.getDhis2Uuid(), date, attributeDtoList);
    }

    private StageDto createStage(MotechEvent event) {

        Map<String, Object> params = event.getParameters();

        /*Get instance uuid*/
        TrackedEntityInstanceMapper instanceMapper = trackedEntityInstanceDataService.
                findByExternalName((String) params.remove(EventParams.EXTERNAL_ID));

        String orgUnit = (String) params.remove(EventParams.LOCATION);
        String orgUnitUuid = getOrgUnitUuid(orgUnit);


        String program = (String) params.remove(EventParams.PROGRAM);

        String date = (String) params.remove(EventParams.DATE);

        /*Get stage UUID. Once dynamic actions are implemented, event should directly pass DHIS2 UUID*/
        String stage = (String) params.remove(EventParams.STAGE);


        List<AttributeDto> stageDataElementDtos = new ArrayList<>();

         addRemainderToDto(params, stageDataElementDtos);

        return new StageDto(program, orgUnitUuid, date, stage, instanceMapper.getDhis2Uuid(), stageDataElementDtos);
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
        addRemainderToDto(params, attributeDtoList);

        return new TrackedEntityInstanceDto(externalUUID, trackedEntity, attributeDtoList, orgUnitUuid);
    }

    private String getOrgUnitUuid(String orgUnit) {

        try {

            return orgUnitDataService.findByName(orgUnit).getUuid();

        } catch (NullPointerException e) {
            logger.error("Org Unit Name not found");
            logger.error(e.toString());
            return null;
        }
    }

    private void addRemainderToDto(Map<String, Object> params, List<AttributeDto> list) {

        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();

            if (entry.getValue() != null) {
                list.add(new AttributeDto(null, (String) entry.getKey(), (String) entry.getValue()));
            }

        }

    }


}
