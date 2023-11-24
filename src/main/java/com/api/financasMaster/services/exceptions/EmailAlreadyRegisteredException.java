package com.api.financasMaster.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmailAlreadyRegisteredException extends Exception {
    public EmailAlreadyRegisteredException(String message) {super(message);}
}
