package com.widehouse.web;

import com.widehouse.event.Event;
import lombok.AllArgsConstructor;

import java.time.format.DateTimeFormatter;

/**
 * Created by kiel on 2016. 5. 24..
 */
public class EventView {
    private Event event;

    public EventView(Event event) {
        this.event = event;
    }

    public String getTitle() {
        return event.getTitle();
    }

    public String getStart() {
        return event.getStartDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String getEnd() {
        return event.getEndDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
