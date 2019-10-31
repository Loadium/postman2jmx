package com.loadium.postman2jmx.exception;

public class NoPostmanCollectionItemException extends Exception {
    public NoPostmanCollectionItemException() {
        super("There is no postman collection item! Please ensure that your postman file has been exported as Collection v2 or v2.1");
    }
}
