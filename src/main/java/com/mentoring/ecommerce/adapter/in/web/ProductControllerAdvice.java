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
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ErrorEntity error = new ErrorEntity(BAD_REQUEST.value(), errors, message, ((ServletWebRequest) request).getRequest().getRequestURI());
        return handleExceptionInternal(
                ex, error, headers, BAD_REQUEST, request);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(final NotFoundException ex, final WebRequest request) {
        final ErrorEntity errorEntity = new ErrorEntity(NOT_FOUND.value(), ex.getMessage(),
                ex.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errorEntity, NOT_FOUND);
    }
}
