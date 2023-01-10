package com.maigrand.overwatchdb.context.event;

import lombok.Getter;
import lombok.Singular;
import org.springframework.context.ApplicationEvent;

import java.util.Collection;

@Getter
public class ErrorEvent extends ApplicationEvent {

    private final Throwable exception;
    private final String threadName;
    private final Collection<? extends CharSequence> messages;

    @lombok.Builder(builderClassName = "Builder")
    public ErrorEvent(
            Object source,
            String threadName,
            @Singular Collection<? extends CharSequence> messages,
            Throwable exception) {
        super(source);
        this.threadName = threadName != null
                ? threadName
                : Thread.currentThread().getName();
        this.messages = messages;
        this.exception = exception;
    }
}
