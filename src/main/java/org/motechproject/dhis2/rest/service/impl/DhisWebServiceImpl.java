package org.motechproject.dhis2.rest.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.motechproject.dhis2.domain.Settings;
import org.motechproject.dhis2.service.DhisWebException;
import org.motechproject.dhis2.rest.service.DhisWebService;
import org.motechproject.dhis2.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("dhisWebService")
public class DhisWebServiceImpl implements DhisWebService {
    private static final String NO_PAGING = "?paging=false";

    @Autowired
    @Qualifier("dhisSettingsService")
    private SettingsService settingsService;

    @Override
    public <T> T getResource(String uri, Class<T>  clazz) {
        Settings settings = settingsService.getSettings();
        HttpUriRequest request = generateHttpRequest(settings, uri);
        InputStream content = getContentForRequest(request);

        T resource;

        try {
            resource = new ObjectMapper().readValue(content, clazz);
        } catch (Exception e) {
            throw new DhisWebException("Error parsing resource", e);
        }

        return resource;
    }

    @Override
    public <T> List<T> getResources(String resourceName, Class<T> clazz) {
        Settings settings = settingsService.getSettings();
        HttpUriRequest request = generateHttpRequest(settings, settings.getResourceURI(resourceName) + NO_PAGING);
        InputStream content = getContentForRequest(request);

        List<T> resources;

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            JavaType type = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
            resources = mapper.reader(type).withRootName(resourceName).readValue(content);
        } catch (Exception e) {
            throw new DhisWebException("Error parsing resources", e);
        }

        return resources;
    }

    private HttpUriRequest generateHttpRequest(Settings settings, String url) {
        HttpUriRequest request = new HttpGet(url);
        request.addHeader("accept", "application/json");
        request.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(settings.getUsername(), settings.getPassword()),
                "UTF-8",
                false
        ));
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
