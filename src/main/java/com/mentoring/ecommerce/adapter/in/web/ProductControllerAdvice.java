package com.mentoring.ecommerce.adapter.in.web;

import com.mentoring.common.exceptions.ProductNotFoundException;
import com.mentoring.ecommerce.adapter.in.web.response.ErrorEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ErrorEntity> handleNotFoundException(
            final RuntimeException exception, final WebRequest request) {

        final ErrorEntity error = new ErrorEntity(NOT_FOUND.value(), exception.getMessage(),
                exception.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, MissingServletRequestParameterException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorEntity> handleBadRequestException(
            final RuntimeException exception, final WebRequest request) {

        final ErrorEntity error = new ErrorEntity(BAD_REQUEST.value(), exception.getMessage(),
                exception.getCause().getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }
}
