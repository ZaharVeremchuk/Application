package com.cosmetology.application.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClientNotFound extends RuntimeException{

    public ClientNotFound(String message) {
        super(message);
    }
}
