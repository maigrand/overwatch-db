package com.maigrand.overwatchdb.exception.handler;

import com.maigrand.overwatchdb.event.ErrorEvent;
import com.maigrand.overwatchdb.payload.ErrorDetails;
import lombok.NonNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private final ApplicationEventPublisher eventPublisher;

    public AppExceptionHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        eventPublisher.publishEvent(new ErrorEvent(this, ex));

        ErrorDetails error = new ErrorDetails(httpStatus, details, ((ServletWebRequest) request).getRequest().getRequestURI());
        return handleExceptionInternal(ex, error, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getReason());

        HttpStatus httpStatus = ex.getStatus();

        ErrorDetails error = new ErrorDetails(httpStatus, details, ((ServletWebRequest) request).getRequest().getRequestURI());
        return handleExceptionInternal(ex, error, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
            WebRequest request) {
        Set<String> messages = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return handleExceptionInternal(ex, messages, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleBindException(
            BindException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {

        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }

        ErrorDetails error = new ErrorDetails(HttpStatus.BAD_REQUEST, details, ((ServletWebRequest) request).getRequest().getRequestURI());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {

        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }

        ErrorDetails error = new ErrorDetails(HttpStatus.BAD_REQUEST, details, ((ServletWebRequest) request).getRequest().getRequestURI());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @NonNull HttpMessageNotReadableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add("Required request body is missing");

        ErrorDetails error = new ErrorDetails(status, details, ((ServletWebRequest) request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex,
            Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {

        if (!(body instanceof ErrorDetails)) {
            List<String> details = new ArrayList<>();
            details.add(ex.getMessage());
            body = new ErrorDetails(status, details, ((ServletWebRequest) request).getRequest().getRequestURI());
        }

        return new ResponseEntity<>(body, status);
    }
}
