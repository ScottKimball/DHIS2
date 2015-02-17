package org.motechproject.dhis2.service;

public class DhisWebException extends RuntimeException {
    public DhisWebException(String message) {
        super(message);
    }

    public DhisWebException(String message, Throwable cause) {
        super(message, cause);
    }
}
