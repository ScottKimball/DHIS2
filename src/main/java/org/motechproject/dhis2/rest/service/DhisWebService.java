package org.motechproject.dhis2.rest.service;

import org.motechproject.dhis2.rest.domain.DataElementDto;
import org.motechproject.dhis2.rest.domain.DhisEventDto;
import org.motechproject.dhis2.rest.domain.DhisStatusResponse;
import org.motechproject.dhis2.rest.domain.EnrollmentDto;
import org.motechproject.dhis2.rest.domain.OrganisationUnitDto;
import org.motechproject.dhis2.rest.domain.ProgramDto;
import org.motechproject.dhis2.rest.domain.ProgramStageDto;
import org.motechproject.dhis2.rest.domain.TrackedEntityAttributeDto;
import org.motechproject.dhis2.rest.domain.TrackedEntityDto;
import org.motechproject.dhis2.rest.domain.TrackedEntityInstanceDto;

import java.util.List;

public interface DhisWebService {
    List<DataElementDto> getDataElements();
    DataElementDto getDataElementByHref(String href);

    List<OrganisationUnitDto> getOrganisationUnits();
    OrganisationUnitDto getOrganisationUnitByHref(String href);

    List<ProgramDto> getPrograms();
    ProgramDto getProgramByHref(String href);

    List<ProgramStageDto> getProgramStages();
    ProgramStageDto getProgramStageByHref(String href);

    List<TrackedEntityAttributeDto> getTrackedEntityAttributes();
    TrackedEntityAttributeDto getTrackedEntityAttributeByHref(String href);

    List<TrackedEntityDto> getTrackedEntities();
    TrackedEntityDto getTrackedEntityByHref(String href);

    DhisStatusResponse createEnrollment(EnrollmentDto enrollmentDto);
    DhisStatusResponse createEvent(DhisEventDto event);
    DhisStatusResponse createTrackedEntityInstance(TrackedEntityInstanceDto trackedEntity);
}


