package com.mentoring.ecommerce.adapter.in.web.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorEntity {
    private LocalDateTime timestamp;
    private int status;
    private List<String> errors;
    private String message;
    private String path;

    public ErrorEntity(final int status, final String error, final String message, final String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        errors = List.of(error);
        this.message = message;
        this.path = path;
    }

    public ErrorEntity(final int status, final List<String> errors, final String message, final String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.errors = errors;
        this.message = message;
        this.path = path;
    }
}
