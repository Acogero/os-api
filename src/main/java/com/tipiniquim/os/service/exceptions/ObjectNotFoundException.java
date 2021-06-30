package com.tipiniquim.os.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -8236298587212183571L;

    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
