package org.motechproject.dhis2.http;

/**
 * Created by scott on 11/25/14.
 */
public class HttpConstants {

    public static final String BASE_URL = "http://admin:district@localhost:8080/api";
    public static final String PROGRAM_PATH = "/programs";
    public static final String TRACKED_ENTITY_ATTRIBUTES_PATH = "/trackedEntityAttributes";
    public static final String ORG_UNITS_PATH = "/organisationUnits";
    public static final String TRACKED_ENTITY_INSTANCES_PATH = "/trackedEntityInstances";
    public static final String EVENTS_PATH ="/events";
    public static final String ENROLLMENTS_PATH = "/enrollments";
    public static final String STAGES_PATH = "/programStages";

    public static final String NO_PAGING_NO_LINKS = "&paging=false&links=false";
    public static final String QUERY = "?query=";
}
