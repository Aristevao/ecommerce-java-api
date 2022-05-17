package com.mentoring.ecommerce.adapter.in.web;

import com.mentoring.ecommerce.adapter.in.web.response.ErrorEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ProductControllerValidAdvice extends ResponseEntityExceptionHandler {
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        final ErrorEntity error1 = new ErrorEntity(BAD_REQUEST.value(), message, message, ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(error1, BAD_REQUEST);
    }
}
