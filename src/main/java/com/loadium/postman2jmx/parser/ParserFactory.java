package com.loadium.postman2jmx.parser;

import com.loadium.postman2jmx.utils.CollectionVersion;

public class ParserFactory {

    public static IParser getParser(CollectionVersion version) {
        if(CollectionVersion.V2 == version) {
            return new PostmanParserV2();
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
