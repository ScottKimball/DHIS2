package org.motechproject.dhis2.rest.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.motechproject.admin.service.StatusMessageService;
import org.motechproject.dhis2.domain.Settings;
import org.motechproject.dhis2.rest.domain.BaseDto;
import org.motechproject.dhis2.rest.domain.DataElementDto;
import org.motechproject.dhis2.rest.domain.DataValueDto;
import org.motechproject.dhis2.rest.domain.DataValueSetDto;
import org.motechproject.dhis2.rest.domain.DhisEventDto;
import org.motechproject.dhis2.rest.domain.DhisStatusResponse;
import org.motechproject.dhis2.rest.domain.EnrollmentDto;
import org.motechproject.dhis2.rest.domain.OrganisationUnitDto;
import org.motechproject.dhis2.rest.domain.ProgramDto;
import org.motechproject.dhis2.rest.domain.ProgramStageDto;
import org.motechproject.dhis2.rest.domain.TrackedEntityAttributeDto;
import org.motechproject.dhis2.rest.domain.TrackedEntityDto;
import org.motechproject.dhis2.rest.domain.TrackedEntityInstanceDto;
import org.motechproject.dhis2.service.DhisWebException;
import org.motechproject.dhis2.rest.service.DhisWebService;
import org.motechproject.dhis2.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of {@link org.motechproject.dhis2.rest.service.DhisWebService}
 */
@Service("dhisWebService")
public class DhisWebServiceImpl implements DhisWebService {
    private static final String MODULE_NAME = "dhis2";
    private static final String API_ENDPOINT = "/api";
    private static final String ENROLLMENTS_PATH = "/enrollments";
    private static final String EVENTS_PATH = "/events";
    private static final String TRACKED_ENTITY_INSTANCES_PATH = "/trackedEntityInstances";
    private static final String DATA_VALUES_PATH = "/dataValues";
    private static final String DATA_VALUE_SETS_PATH = "/dataValueSets";

    private static final String DATA_ELEMENTS = "dataElements";
    private static final String ORG_UNITS = "organisationUnits";
    private static final String PROGRAMS = "programs";
    private static final String PROGRAM_STAGES = "programStages";
    private static final String TRACKED_ENTITIES = "trackedEntities";
    private static final String TRACKED_ENITTY_ATTRIBUTES = "trackedEntityAttributes";

    private static final String NO_PAGING = "?paging=false";

    public static final List<Integer> ACCEPTABLE_DHIS_RESPONSE_STATUSES = Arrays.asList(HttpStatus.SC_OK,
            HttpStatus.SC_ACCEPTED, HttpStatus.SC_CREATED);

    @Autowired
    private StatusMessageService statusMessageService;

    @Autowired
    @Qualifier("dhisSettingsService")
    private SettingsService settingsService;

    public DhisWebServiceImpl() {
    }

    public DhisWebServiceImpl(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @Override
    public List<DataElementDto> getDataElements() {
        return getResources(DATA_ELEMENTS, DataElementDto.class);
    }

    @Override
    public DataElementDto getDataElementByHref(String href) {
        return getResource(href, DataElementDto.class);
    }

    @Override
    public List<OrganisationUnitDto> getOrganisationUnits() {
        return getResources(ORG_UNITS, OrganisationUnitDto.class);
    }

    @Override
    public OrganisationUnitDto getOrganisationUnitByHref(String href) {
        return getResource(href, OrganisationUnitDto.class);
    }

    @Override
    public List<ProgramDto> getPrograms() {
        return getResources(PROGRAMS, ProgramDto.class);
    }

    @Override
    public ProgramDto getProgramByHref(String href) {
        return getResource(href, ProgramDto.class);
    }

    @Override
    public List<ProgramStageDto> getProgramStages() {
        return getResources(PROGRAM_STAGES, ProgramStageDto.class);
    }

    @Override
    public ProgramStageDto getProgramStageByHref(String href) {
        return getResource(href, ProgramStageDto.class);
    }

    @Override
    public List<TrackedEntityDto> getTrackedEntities() {
        return getResources(TRACKED_ENTITIES, TrackedEntityDto.class);
    }

    @Override
    public TrackedEntityDto getTrackedEntityByHref(String href) {
        return getResource(href, TrackedEntityDto.class);
    }

    @Override
    public List<TrackedEntityAttributeDto> getTrackedEntityAttributes() {
        return getResources(TRACKED_ENITTY_ATTRIBUTES, TrackedEntityAttributeDto.class);
    }

    @Override
    public TrackedEntityAttributeDto getTrackedEntityAttributeByHref(String href) {
        return getResource(href, TrackedEntityAttributeDto.class);
    }

    @Override
    public DhisStatusResponse createEnrollment(EnrollmentDto enrollment) {
        String json = parseToJson(enrollment);
        Settings settings = settingsService.getSettings();

        return createEntity(settings, settings.getServerURI() + API_ENDPOINT + ENROLLMENTS_PATH, json);
    }

    @Override
    public DhisStatusResponse createEvent(DhisEventDto event) {
        String json = parseToJson(event);
        Settings settings = settingsService.getSettings();

        return createEntity(settings, settings.getServerURI() + API_ENDPOINT + EVENTS_PATH, json);
    }

    @Override
    public DhisStatusResponse createTrackedEntityInstance(TrackedEntityInstanceDto trackedEntity) {
        String json = parseToJson(trackedEntity);
        Settings settings = settingsService.getSettings();

        return createEntity(settings, settings.getServerURI() + API_ENDPOINT + TRACKED_ENTITY_INSTANCES_PATH, json);
    }

    @Override
    public DhisStatusResponse sendDataValue(DataValueDto dataValueDto) {
        String json = parseToJson(dataValueDto);
        Settings settings = settingsService.getSettings();

        return createEntity(settings, settings.getServerURI() + API_ENDPOINT + DATA_VALUES_PATH, json);
    }

    @Override
    public DhisStatusResponse sendDataValueSet(DataValueSetDto dataValueSetDto) {
        String json = parseToJson(dataValueSetDto);
        Settings settings = settingsService.getSettings();

        return createEntity(settings, settings.getServerURI() + API_ENDPOINT + DATA_VALUE_SETS_PATH, json);
    }

    /*Gets the resource in the form of a dto*/
    private <T extends BaseDto> T getResource(String uri, Class<T>  clazz) {
        Settings settings = settingsService.getSettings();
        HttpUriRequest request = generateHttpRequest(settings, uri);
        HttpResponse response = getResponseForRequest(request);

        InputStream content = getContentForResponse(response);

        T resource;

        try {
            resource = new ObjectMapper().readValue(content, clazz);
        } catch (Exception e) {
            String msg = String.format("Error parsing resource at uri: %s, exception: %s", uri, e.toString());
            statusMessageService.warn(msg, MODULE_NAME);
            throw new DhisWebException(msg, e);
        }

        return resource;
    }

    /*Gets a list of dtos*/
    private <T extends BaseDto> List<T> getResources(String resourceName, Class<T> clazz) {
        Settings settings = settingsService.getSettings();
        HttpUriRequest request = generateHttpRequest(settings, getURIForResource(settings.getServerURI(), resourceName));
        HttpResponse response = getResponseForRequest(request);
        InputStream content = getContentForResponse(response);

        List<T> resources;

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, clazz);
            resources = mapper.reader(type).withRootName(resourceName).readValue(content);
        } catch (Exception e) {
            String msg = String.format("Error parsing %s resources, exception: %s", resourceName, e.toString());
            statusMessageService.warn(msg, MODULE_NAME);
            throw new DhisWebException(msg, e);
        }

        return resources;
    }

    /*Attempts to create the an entity in the DHIS2 system. Returns the response from DHIS2*/
    private DhisStatusResponse createEntity(Settings settings, String uri, String json) {
        HttpUriRequest request = generatePostRequest(settings, uri, json);
        HttpResponse response = getResponseForRequest(request);
        InputStream content = getContentForResponse(response);

        DhisStatusResponse status;

        try {
            status = new ObjectMapper().readValue(content, DhisStatusResponse.class);
        } catch (Exception e) {
            String msg = String.format("Error parsing response from uri: %s, exception: %s", uri, e.toString());
            statusMessageService.warn(msg, MODULE_NAME);
            throw new DhisWebException(msg, e);
        }

        return status;
    }



    /*Converts the object to json*/
    private String parseToJson(Object object) {
        String json;

        try {
            json = new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            String msg = String.format("Error parsing object: %s to json, exception: %s", object.toString(), e.toString());
            statusMessageService.warn(msg, MODULE_NAME);
            throw new DhisWebException(msg, e);
        }
        return json;
    }

    /*Generates an HTTP get request*/
    private HttpUriRequest generateHttpRequest(Settings settings, String url) {
        HttpGet request = new HttpGet(url);
        request.addHeader("accept", "application/json");
        request.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(settings.getUsername(), settings.getPassword()),
                "UTF-8",
                false
        ));
        return request;
    }

    /*Generates an HTTP post request*/
    private HttpUriRequest generatePostRequest(Settings settings, String url, String body) {
        HttpPost request = new HttpPost(url);
        request.addHeader("Content-Type", "application/json");
        request.addHeader("accept", "application/json");
        request.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(settings.getUsername(), settings.getPassword()),
                "UTF-8",
                false
        ));

        StringEntity entity;

        try {
            entity = new StringEntity(body, "UTF-8");
        } catch (Exception e) {
            String msg = String.format("Error creating entity from body: %s, exception: %s", body, e.toString());
            statusMessageService.warn(msg, MODULE_NAME);
            throw new DhisWebException(msg, e);
        }

        request.setEntity(entity);

        return request;
    }

    /*Attempts an HTTP request. Returns the response*/
    private HttpResponse getResponseForRequest(HttpUriRequest request) {
        HttpResponse response;

        try {
            response = new DefaultHttpClient().execute(request);
        } catch (Exception e) {
            String msg = String.format("Error receiving response for request: %s", request.toString());
            statusMessageService.warn(msg, MODULE_NAME);
            throw new DhisWebException(msg, e);
        }

        StatusLine statusLine = response.getStatusLine();

        if (!ACCEPTABLE_DHIS_RESPONSE_STATUSES.contains(statusLine.getStatusCode())) {
            String msg = String.format("Error making DHIS request: %s", statusLine.toString());
            statusMessageService.warn(msg, MODULE_NAME);
            throw new DhisWebException(msg);
        }

        return response;
    }

    /*Gets an input stream from the HTTP response*/
    private InputStream getContentForResponse(HttpResponse response) {
        InputStream content;

        try {
            content = response.getEntity().getContent();
        } catch (Exception e) {
            String msg = String.format("Error accessing content for response: %s", response.toString());
            statusMessageService.warn(msg, MODULE_NAME);
            throw new DhisWebException(msg, e);
        }

        return content;
    }

    /*Builds the URL for a particular resource*/
    private String getURIForResource(String baseURI, String resourceName) {
        return String.format(baseURI + "/api/%s" + NO_PAGING, resourceName);
    }
}
