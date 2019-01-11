package com.loadium.postman2jmx.model.postman;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.loadium.postman2jmx.utils.ValueUtils;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostmanItem implements Comparable<PostmanItem> {

    @JsonProperty("item")
    List<PostmanItem> items = new ArrayList<>();

    @JsonProperty("name")
    private String name;

    @JsonProperty("request")
    private PostmanRequest request;

    @JsonProperty("event")
    private List<PostmanEvent> events = new ArrayList<>();

    public PostmanItem() {
    }

    public PostmanItem(String name, PostmanRequest request, List<PostmanEvent> events) {
        this.name = name;
        this.request = request;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ValueUtils.value(name);
    }

    public PostmanRequest getRequest() {
        return request;
    }

    public void setRequest(PostmanRequest request) {
        this.request = request;
    }

    public List<PostmanItem> getItems() {
        return items;
    }

    public void setItems(List<PostmanItem> items) {
        this.items = items;
    }

    public List<PostmanEvent> getEvents() {
        return events;
    }

    public void setEvents(List<PostmanEvent> events) {
        this.events = events;
    }

    @Override
    public int compareTo(PostmanItem o) {
        return this.name.compareTo(o.name);
    }
}
