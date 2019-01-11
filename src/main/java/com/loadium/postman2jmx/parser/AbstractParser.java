package com.loadium.postman2jmx.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loadium.postman2jmx.model.postman.PostmanCollection;
import com.loadium.postman2jmx.model.postman.PostmanItem;
import com.loadium.postman2jmx.utils.PostmanCollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public abstract class AbstractParser implements IParser {

    private ObjectMapper objectMapper;

    public AbstractParser() {
        objectMapper = new ObjectMapper();
    }

    protected ObjectMapper getMapper() {
        return this.objectMapper;
    }

    protected  PostmanCollection readValue(String fileName) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(fileName));
        PostmanCollection postmanCollection = getMapper().readValue(jsonData, PostmanCollection.class);
        getItems(postmanCollection);
        return postmanCollection;
    }

    protected  PostmanCollection readValue(InputStream is) throws IOException {
        PostmanCollection postmanCollection = getMapper().readValue(is, PostmanCollection.class);
        getItems(postmanCollection);
        return postmanCollection;
    }

    protected void getItems(PostmanCollection postmanCollection) {
        List<PostmanItem> items = PostmanCollectionUtils.getItems(postmanCollection);
        Collections.sort(items);
        postmanCollection.setItems(items);
    }
}
