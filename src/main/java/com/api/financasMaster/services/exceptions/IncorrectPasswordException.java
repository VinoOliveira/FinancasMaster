package com.api.financasMaster.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class IncorrectPasswordException extends Exception {

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
