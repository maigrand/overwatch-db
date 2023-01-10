package com.maigrand.overwatchdb.payload;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Getter
public class ErrorDetails {

    private final String timestamp;

    private final int status;

    private final String error;

    private final Object message;

    private final String path;

    public ErrorDetails(HttpStatusCode statusCode, String reason, Collection<String> messages, String path) {
        this.status = statusCode.value();
        this.error = reason;
        this.message = messages.size() == 1 ? messages.stream().findFirst().orElse(null) : messages;
        this.path = path;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        this.timestamp = format.format(new Date());
    }
}
