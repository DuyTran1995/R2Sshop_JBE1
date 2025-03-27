package com.jbe01.r2sshop.controller;

import com.jbe01.r2sshop.handler.ErrorResponse;
import com.jbe01.r2sshop.handler.error.BadRequestException;
import com.jbe01.r2sshop.handler.error.ForbiddenException;
import com.jbe01.r2sshop.handler.error.NotFoundException;
import com.jbe01.r2sshop.handler.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse error = ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = ErrorResponse.of(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        ErrorResponse error = ErrorResponse.of(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex) {
        ErrorResponse error = ErrorResponse.of(HttpStatus.FORBIDDEN, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
