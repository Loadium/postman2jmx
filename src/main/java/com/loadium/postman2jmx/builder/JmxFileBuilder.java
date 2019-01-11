package com.loadium.postman2jmx.builder;

import com.loadium.postman2jmx.model.jmx.JmxFile;
import com.loadium.postman2jmx.model.postman.PostmanCollection;

public class JmxFileBuilder extends AbstractJmxFileBuilder {

    @Override
    public JmxFile build(PostmanCollection postmanCollection, String jmxOutputFilePath) throws Exception {
        return super.buildJmxFile(postmanCollection, jmxOutputFilePath);
    }
}
