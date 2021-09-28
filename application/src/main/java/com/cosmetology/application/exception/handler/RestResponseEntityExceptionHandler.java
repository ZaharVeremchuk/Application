package com.cosmetology.application.exception.handler;

import com.cosmetology.application.exception.exceptions.ClientBadRequest;
import com.cosmetology.application.exception.exceptions.ClientNotFound;
import com.cosmetology.application.exception.exceptions.QuestionBadRequest;
import com.cosmetology.application.exception.exceptions.QuestionNotFound;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(QuestionNotFound.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            QuestionNotFound ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(QuestionBadRequest.class)
    protected ResponseEntity<Object> handleQuestionBadRequest(QuestionBadRequest ex){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ClientNotFound.class)
    protected ResponseEntity<Object> handleClientNotFound(ClientNotFound ex){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ClientBadRequest.class)
    protected ResponseEntity<Object> handleClientBadRequest(ClientBadRequest ex){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

}
