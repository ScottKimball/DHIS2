package org.motechproject.dhis2.rest.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.motechproject.dhis2.domain.Settings;
import org.motechproject.dhis2.rest.domain.DataElementDto;
import org.motechproject.dhis2.rest.domain.DhisEventDto;
import org.motechproject.dhis2.rest.domain.EnrollmentDto;
import org.motechproject.dhis2.rest.domain.OrganisationUnitDto;
import org.motechproject.dhis2.rest.domain.ProgramDto;
import org.motechproject.dhis2.rest.domain.ProgramStageDto;
import org.motechproject.dhis2.rest.domain.Resource;
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
import java.util.List;

@Service("dhisWebService")
public class DhisWebServiceImpl implements DhisWebService {
    private static final String DATA_ELEMENTS = "dataElements";
    private static final String ORG_UNITS = "organisationUnits";
    private static final String PROGRAMS = "programs";
    private static final String PROGRAM_STAGES = "programStages";
    private static final String TRACKED_ENTITIES = "trackedEntities";
    private static final String TRACKED_ENITTY_ATTRIBUTES = "trackedEntityAttributes";
    private static final String NO_PAGING = "?paging=false";

    @Autowired
    @Qualifier("dhisSettingsService")
    private SettingsService settingsService;

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

    private <T extends Resource> T getResource(String uri, Class<T>  clazz) {
        Settings settings = settingsService.getSettings();
        HttpUriRequest request = generateHttpRequest(settings, uri);
        InputStream content = getContentForRequest(request);

        T resource;

        try {
            resource = new ObjectMapper().readValue(content, clazz);
        } catch (Exception e) {
            throw new DhisWebException("Error parsing resource at uri: " + uri, e);
        }

        return resource;
    }

    private <T extends Resource> List<T> getResources(String resourceName, Class<T> clazz) {
        Settings settings = settingsService.getSettings();
        HttpUriRequest request = generateHttpRequest(settings, settings.getResourceURI(resourceName) + NO_PAGING);
        InputStream content = getContentForRequest(request);

        List<T> resources;

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, clazz);
            resources = mapper.reader(type).withRootName(resourceName).readValue(content);
        } catch (Exception e) {
            throw new DhisWebException("Error parsing resources", e);
        }

        return resources;
    }

    @Override
    public String createEnrollment(EnrollmentDto enrollment) {
        Settings settings = settingsService.getSettings();
        ObjectMapper mapper = new ObjectMapper();
        String json;

        try {
            json = mapper.writeValueAsString(enrollment);
        } catch (JsonProcessingException e) {
            throw new DhisWebException("Unable to parse enrollment", e);
        }

        HttpUriRequest request = generatePostRequest(settings, settings.getEnrollmentsURI(), json);
        InputStream content = getContentForRequest(request);

        return content.toString();
    }

    @Override
    public String createEvent(DhisEventDto event) {
        Settings settings = settingsService.getSettings();
        ObjectMapper mapper = new ObjectMapper();
        String json;

        try {
            json = mapper.writeValueAsString(event);
        } catch (Exception e) {
            throw new DhisWebException("Unable to parse event", e);
        }

        HttpUriRequest request = generatePostRequest(settings, settings.getEventsURI(), json);
        InputStream content = getContentForRequest(request);

        return content.toString();
    }

    @Override
    public String createTrackedEntityInstance(TrackedEntityInstanceDto trackedEntity) {
        Settings settings = settingsService.getSettings();
        ObjectMapper mapper = new ObjectMapper();
        String json;

        try {
            json = mapper.writeValueAsString(trackedEntity);
        } catch (Exception e) {
            throw new DhisWebException("Unable to parse tracked entity instance", e);
        }

        HttpUriRequest request = generatePostRequest(settings, settings.getTrackedEntityInstancesURI(), json);
        InputStream content = getContentForRequest(request);

        return content.toString();
    }

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
            throw new DhisWebException("Error creating string entity", e);
        }
        request.setEntity(entity);
        return request;
    }

    private InputStream getContentForRequest(HttpUriRequest request) {
        HttpResponse response;
        InputStream content;

        try {
            response = new DefaultHttpClient().execute(request);
        } catch (Exception e) {
            throw new DhisWebException("Error making request", e);
        }

        try {
            content = response.getEntity().getContent();
        } catch (Exception e) {
            throw new DhisWebException("Error getting content for request", e);
        }

        return content;
    }
}
