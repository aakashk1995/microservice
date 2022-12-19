package com.example.userservice.exception;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice(basePackages = "com.example.userservice")
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = { UserNotFoundException.class })
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setError(ex.getLocalizedMessage());
        errorResponse.setMessage(ExceptionUtils.getStackTrace(ex));
        errorResponse.setPath(request.getContextPath());
        LOGGER.error("Exception occurred while finding user  ",ex);

        return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setError(ex.getMessage());
        errorResponse.setMessage(ExceptionUtils.getStackTrace(ex));
        LOGGER.error("Exception occurred ",ex);

        return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }




}
