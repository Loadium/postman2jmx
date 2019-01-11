package com.loadium.postman2jmx.model.postman;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostmanRequest {

    @JsonProperty("url")
    private PostmanUrl url;

    @JsonProperty("method")
    private String method;

    @JsonProperty("header")
    private List<PostmanHeader> headers = new ArrayList<>();

    @JsonProperty("body")
    private PostmanBody body;

    @JsonProperty("description")
    private String description;

    public PostmanRequest() {
    }

    public List<PostmanHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<PostmanHeader> headers) {
        this.headers = headers;
    }

    public PostmanBody getBody() {
        return body;
    }

    public void setBody(PostmanBody body) {
        this.body = body;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PostmanUrl getUrl() {
        return url;
    }

    public void setUrl(PostmanUrl url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


}

