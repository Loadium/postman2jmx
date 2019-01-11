package com.loadium.postman2jmx.exception;

public class NullPostmanCollectionException extends Exception {
    public NullPostmanCollectionException() {
        super("Postman collection model can not be null!");
    }
}
