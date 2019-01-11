package com.loadium.postman2jmx.model.postman;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostmanInfo {

    @JsonProperty("name")
    private String name;

    @JsonProperty("_postman_id")
    private String postmanId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("schema")
    private String schema;

    public PostmanInfo() {
    }

    public PostmanInfo(String name, String postmanId, String description, String schema) {
        this.name = name;
        this.postmanId = postmanId;
        this.description = description;
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostmanId() {
        return postmanId;
    }

    public void setPostmanId(String postmanId) {
        this.postmanId = postmanId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
