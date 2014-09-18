package org.motechproject.dhis2.http;

/**
 * Created by scott on 9/17/14.
 */
public class Request {

    private String Url;
    private String jsonBody;

    public Request(String url, String jsonBody) {
        Url = url;
        this.jsonBody = jsonBody;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getJsonBody() {
        return jsonBody;
    }

    public void setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
    }
}
