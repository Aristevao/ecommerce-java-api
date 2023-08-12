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

    public ErrorEntity(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        errors = List.of(error);
        this.message = message;
        this.path = path;
    }

    public ErrorEntity(int status, List<String> errors, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.errors = errors;
        this.message = message;
        this.path = path;
    }
}
