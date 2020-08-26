package com.loadium.postman2jmx.app;

import com.loadium.postman2jmx.builder.JmxFileBuilder;
import com.loadium.postman2jmx.exception.InvalidArgumentsException;
import com.loadium.postman2jmx.model.postman.PostmanCollection;
import com.loadium.postman2jmx.parser.IParser;
import com.loadium.postman2jmx.parser.ParserFactory;
import com.loadium.postman2jmx.utils.CollectionVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Postman2Jmx {

    private static Logger logger = LoggerFactory.getLogger(Postman2Jmx.class.getName());


    public static void main(String[] args) {

        try {
            if (args.length != 2) {
                throw new InvalidArgumentsException();
            }

            String postmanCollectionJson = args[0];
            String jmxOutputFile = args[1];

            logger.info("Trying to parse postman collection file: {}", postmanCollectionJson);
            IParser parser = ParserFactory.getParser(CollectionVersion.V2);
            PostmanCollection postmanCollection = parser.parse(postmanCollectionJson);
            logger.info("Successfully parsed postman collection file: {}, Total parsed item count: {} ", postmanCollectionJson, postmanCollection.getItems().size());

            logger.info("Trying to build jmx file: {}", jmxOutputFile);
            JmxFileBuilder jmxFileBuilder = new JmxFileBuilder();
            jmxFileBuilder.build(postmanCollection, jmxOutputFile);
            logger.info("Successfully build jmx file: {} ", jmxOutputFile);

        } catch (Exception e) {
            logger.error("Error occurred!", e);
        }
    }


}
