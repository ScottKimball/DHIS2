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
import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.rest.domain.EnrollmentDto;
import org.motechproject.dhis2.rest.domain.Resource;
import org.motechproject.dhis2.service.DhisWebException;
import org.motechproject.dhis2.rest.service.DhisWebService;
import org.motechproject.dhis2.service.SettingsService;
import org.motechproject.event.MotechEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service("dhisWebService")
public class DhisWebServiceImpl implements DhisWebService {
    private static final String NO_PAGING = "?paging=false";

    @Autowired
    @Qualifier("dhisSettingsService")
    private SettingsService settingsService;

    @Override
    public <T extends Resource> T getResource(String uri, Class<T>  clazz) {
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

    @Override
    public <T extends Resource> List<T> getResources(String resourceName, Class<T> clazz) {
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
            throw new DhisWebException("Unable to parse enrollment");
        }

        HttpUriRequest request = generatePostRequest(settings, settings.getEnrollmentsURI(), json);
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
