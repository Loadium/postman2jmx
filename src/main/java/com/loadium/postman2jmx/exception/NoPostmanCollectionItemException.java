package com.loadium.postman2jmx.exception;

public class NoPostmanCollectionItemException extends Exception {
    public NoPostmanCollectionItemException() {
        super("There is no postman collection item!");
    }
}
