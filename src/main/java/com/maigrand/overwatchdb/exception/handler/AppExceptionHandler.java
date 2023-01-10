package com.maigrand.overwatchdb.exception.handler;

import com.maigrand.overwatchdb.context.event.ErrorEvent;
import com.maigrand.overwatchdb.payload.ErrorDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collection;
import java.util.Collections;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private final ApplicationEventPublisher eventPublisher;

    public AppExceptionHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(
            Exception exc,
            WebRequest request) {

        HttpStatusCode httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
//        if (exc instanceof AuthenticationException) {
//            httpStatus = BAD_REQUEST;
//        } else if (exc instanceof AccessDeniedException) {
//            httpStatus = FORBIDDEN;
//        }

        Collection<String> messages = getMessages(exc);
        if (httpStatusCode == HttpStatus.INTERNAL_SERVER_ERROR) {
            this.eventPublisher.publishEvent(ErrorEvent.builder()
                    .source(this)
                    .messages(messages)
                    .exception(exc)
                    .build());
        }

        return handleExceptionInternal(exc, messages, httpStatusCode, request);
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected final ResponseEntity<Object> handleResponseStatusException(
            ResponseStatusException exc,
            WebRequest request) {
        Collection<String> messages = getMessages(exc);
        HttpStatusCode httpStatusCode = exc.getStatusCode();
        return handleExceptionInternal(exc, messages, httpStatusCode, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception exc,
            @NonNull Collection<String> messages,
            @NonNull HttpStatusCode httpStatusCode,
            @NonNull WebRequest request) {
        String reason = exc instanceof ResponseStatusException ? ((ResponseStatusException) exc).getReason() : "";
        ErrorDetails error = createErrorDetails(httpStatusCode, reason, messages, request);
        return handleExceptionInternal(exc, error, new HttpHeaders(), httpStatusCode, request);
    }

    @NonNull
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception exc,
            @Nullable Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode httpStatusCode,
            @NonNull WebRequest request) {
        if (!(body instanceof ErrorDetails)) {
            Collection<String> messages = getMessages(exc);
            String reason = exc instanceof ResponseStatusException ? ((ResponseStatusException) exc).getReason() : "";
            body = createErrorDetails(httpStatusCode, reason, messages, request);
        }

        return new ResponseEntity<>(body, httpStatusCode);
    }

    protected Collection<String> getMessages(Throwable exc) {
        String message = exc instanceof ResponseStatusException
                ? ((ResponseStatusException) exc).getReason()
                : exc.getMessage();
        if (StringUtils.isBlank(message)) {
            String exceptionName = exc.getClass().getName();
            StackTraceElement[] stackTrace = exc.getStackTrace();
            message = String.format("%s: %s", exceptionName, stackTrace[0]);
        }
        return Collections.singleton(message);
    }

    protected ErrorDetails createErrorDetails(
            HttpStatusCode httpStatusCode,
            String reason,
            Collection<String> messages,
            WebRequest request) {
        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();
        return new ErrorDetails(httpStatusCode, reason, messages, requestURI);
    }
}
