package com.tipiniquim.os.service.exceptions;

public class DataIntegratyViolationException extends RuntimeException {
    private static final long serialVersionUID = -8236298587212183571L;

    public DataIntegratyViolationException() {
    }

    public DataIntegratyViolationException(String message) {
        super(message);
    }

    public DataIntegratyViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
