package com.jbe01.r2sshop.handler;

import com.jbe01.r2sshop.model.Metadata;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class SuccessResponse<T> {
    private int statusCode;
    private String message;
    private T data;
    private Metadata metadata;


    public static <T> SuccessResponse<T> of(T data) {
        return SuccessResponse.<T>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Request successfully")
                .data(data)
                .build();
    }

    public static <T> SuccessResponse<T> of(T data, Metadata metadata) {
        return SuccessResponse.<T>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Request successfully")
                .data(data)
                .metadata(metadata)
                .build();
    }

    public static <T> SuccessResponse<T> of(HttpStatus statusCode, String message, T data) {
        return SuccessResponse.<T>builder()
                .statusCode(statusCode.value())
                .message(message)
                .data(data)
                .build();
    }

    public static <T> SuccessResponse<T> of(HttpStatus statusCode, String message, T data, Metadata metadata) {
        return SuccessResponse.<T>builder()
                .statusCode(statusCode.value())
                .message(message)
                .data(data)
                .metadata(metadata)
                .build();
    }

    public ResponseEntity<SuccessResponse<T>> toResponseEntity() {
        return new ResponseEntity<>(this, HttpStatusCode.valueOf(this.statusCode));
    }
}