package com.maigrand.overwatchdb.event.listener;

import com.maigrand.overwatchdb.event.ErrorEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ErrorLogger implements ApplicationListener<ErrorEvent> {

    private final Logger logger = LoggerFactory.getLogger(ErrorLogger.class);

    @Override
    public void onApplicationEvent(ErrorEvent event) {
        Throwable throwable = event.getThrowable();
        logger.error(throwable.getMessage(), throwable);
    }
}
