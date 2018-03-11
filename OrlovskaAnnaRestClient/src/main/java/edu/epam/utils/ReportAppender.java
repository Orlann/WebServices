package edu.epam.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.util.ArrayList;
import java.util.List;

public class ReportAppender extends AppenderSkeleton {

    private List<LoggingEvent> eventList = new ArrayList<>();

    @Override
    protected void append(LoggingEvent loggingEvent) {
        eventList.add(loggingEvent);
    }

    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}