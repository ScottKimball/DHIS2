package org.motechproject.dhis2.rest.service;

import org.motechproject.dhis2.rest.domain.EnrollmentDto;
import org.motechproject.dhis2.rest.domain.Resource;
import org.motechproject.event.MotechEvent;

import java.util.List;

public interface DhisWebService {
    <T extends Resource> T getResource(String uri, Class<T> clazz);
    <T extends Resource> List<T> getResources(String name, Class<T> clazz);
    String createEnrollment(EnrollmentDto enrollmentDto);
}


