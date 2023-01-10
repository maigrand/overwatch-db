package com.maigrand.overwatchdb.payload;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
public class ErrorDetails {

    private final String timestamp;

    private final int status;

    private final String error;

    private final List<String> messages;

    private final String path;

    public ErrorDetails(HttpStatus status, List<String> messages, String path) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.messages = messages;
        this.path = path;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        this.timestamp = simpleDateFormat.format(new Date());
    }
}
