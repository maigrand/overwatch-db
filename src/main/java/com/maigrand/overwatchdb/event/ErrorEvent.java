package com.maigrand.overwatchdb.event;

import org.springframework.context.ApplicationEvent;

public class ErrorEvent extends ApplicationEvent {

    private final Throwable throwable;

    public ErrorEvent(Object source, Throwable throwable) {
        super(source);
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
