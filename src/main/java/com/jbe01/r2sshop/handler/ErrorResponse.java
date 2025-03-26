package com.jbe01.r2sshop.handler;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ErrorResponse {
    int status;
    HttpStatus httpStatus;
    String message;


    public static ErrorResponse of(HttpStatus status, String message) {
        return ErrorResponse.builder()
                .status(status.value())
                .httpStatus(status)
                .message(message)
                .build();
    }
    public static ErrorResponse of(HttpStatus status) {
        return ErrorResponse.builder()
                .status(status.value())
                .httpStatus(status)
                .message(status.getReasonPhrase())
                .build();
    }
    public ResponseEntity<ErrorResponse> toResponseEntity(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }
}
