package org.motechproject.dhis2.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * DHIS 2 server configuration details.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings {
    private static final String API_ENDPOINT = "/api";
    private static final String PROGRAM_PATH = "/programs";
    private static final String TRACKED_ENTITY_ATTRIBUTES_PATH = "/trackedEntityAttributes";
    private static final String ORG_UNITS_PATH = "/organisationUnits";
    private static final String TRACKED_ENTITY_INSTANCES_PATH = "/trackedEntityInstances";
    private static final String EVENTS_PATH = "/events";
    private static final String ENROLLMENTS_PATH = "/enrollments";
    private static final String STAGES_PATH = "/programStages";
    private static final String TRACKED_ENTITY_PATH = "/trackedEntities";
    private static final String DATA_ELEMENTS_PATH = "/dataElements";

    private String serverURI;
    private String username;
    private String password;

    public Settings() { }

    public Settings(String serverURI, String username, String password) {
        this.serverURI = serverURI;
        this.username = username;
        this.password = password;
    }

    public String getServerURI() {
        return serverURI;
    }

    public void setServerURI(String serverURI) {
        this.serverURI = serverURI;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProgramURI() {
        return serverURI + API_ENDPOINT + PROGRAM_PATH;
    }

    public String getTrackedEntityAttributesURI() {
        return serverURI + API_ENDPOINT + TRACKED_ENTITY_ATTRIBUTES_PATH;
    }

    public String getOrgUnitsURI() {
        return serverURI + API_ENDPOINT + ORG_UNITS_PATH;
    }

    public String getTrackedEntityInstancesURI() {
        return serverURI + API_ENDPOINT + TRACKED_ENTITY_INSTANCES_PATH;
    }

    public String getEventsURI() {
        return serverURI + API_ENDPOINT + EVENTS_PATH;
    }

    public String getEnrollmentsURI() {
        return serverURI + API_ENDPOINT + ENROLLMENTS_PATH;
    }

    public String getStagesURI() {
        return serverURI + API_ENDPOINT + STAGES_PATH;
    }

    public String getTrackedEntityURI() {
        return serverURI + API_ENDPOINT + TRACKED_ENTITY_PATH;
    }

    public String getDataElementsURI() {
        return serverURI + API_ENDPOINT + DATA_ELEMENTS_PATH;
    }
}
