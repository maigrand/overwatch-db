package com.maigrand.overwatchdb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {

    public EntityNotFoundException(CharSequence message) {
        super(HttpStatus.NOT_FOUND, String.valueOf(message));
    }
}
