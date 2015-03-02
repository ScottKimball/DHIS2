package org.motechproject.dhis2.service;

public class TrackedEntityInstanceMappingException extends RuntimeException {
    public TrackedEntityInstanceMappingException(String message) {
        super(message);
    }

    public TrackedEntityInstanceMappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
