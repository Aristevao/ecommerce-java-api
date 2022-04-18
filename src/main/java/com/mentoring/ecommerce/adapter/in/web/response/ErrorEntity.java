package com.mentoring.ecommerce.adapter.in.web.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorEntity {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorEntity(final int status, final String error, final String message, final String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
