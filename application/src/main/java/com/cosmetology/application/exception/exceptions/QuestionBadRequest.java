package com.cosmetology.application.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QuestionBadRequest extends RuntimeException{

    public QuestionBadRequest(String message) {
        super(message);
    }
}
