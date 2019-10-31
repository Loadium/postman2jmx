package com.loadium.postman2jmx.exception;

public class NullPostmanCollectionException extends Exception {
    public NullPostmanCollectionException() {
        super("Postman collection model can not be null! Please ensure that your postman file has been exported as Collection v2 or v2.1");
    }
}
