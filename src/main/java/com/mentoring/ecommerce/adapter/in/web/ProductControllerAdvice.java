package com.mentoring.ecommerce.adapter.in.web;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.ArrayList;
import java.util.List;

import com.mentoring.common.exceptions.NotFoundException;
import com.mentoring.ecommerce.adapter.in.web.dto.ErrorEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        logger.info(ex.getClass().getName());
        String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ErrorEntity error = new ErrorEntity(BAD_REQUEST.value(), errors, message, // TODO: Replace with builder
                ((ServletWebRequest) request).getRequest().getRequestURI());
        return handleExceptionInternal(
                ex, error, headers, BAD_REQUEST, request);
    }

    // TODO: Common error. Generalize. Probably inheritance
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ErrorEntity errorEntity = new ErrorEntity(NOT_FOUND.value(), ex.getMessage(), // TODO: Replace with builder
                ex.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errorEntity, NOT_FOUND);
    }
}
