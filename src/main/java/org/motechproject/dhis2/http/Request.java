package org.motechproject.dhis2.http;

/**
 * Created by scott on 9/17/14.
 */
public class Request {

    private String baseUrl = HttpConstants.BASE_URL;
    private String url;
    private String body;

    public Request(String url, String body) {

        this.url = baseUrl + url.replace(' ', '+');
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
