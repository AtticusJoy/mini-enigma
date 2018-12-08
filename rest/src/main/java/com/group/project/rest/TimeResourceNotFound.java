package com.group.project.rest;

public class TimeResourceNotFound extends RuntimeException {

    public TimeResourceNotFound() {
    }

    public TimeResourceNotFound(String message) {
        super(message);
    }

    public TimeResourceNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeResourceNotFound(Throwable cause) {
        super(cause);
    }

    public TimeResourceNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
