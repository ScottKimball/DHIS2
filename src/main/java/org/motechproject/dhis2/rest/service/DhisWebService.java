package org.motechproject.dhis2.rest.service;

import java.util.List;

public interface DhisWebService {
    <T> T getResource(String uri, Class<T> clazz);
    <T> List<T> getResources(String name, Class<T> clazz);
}


