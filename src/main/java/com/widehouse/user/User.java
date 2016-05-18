package com.widehouse.user;

import com.widehouse.event.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kiel on 2016. 5. 18..
 */
@Getter
@Setter
public class User {
    Long id;

    String email;

    Set<Event> events;

    public void addEvent(Event event) {
        if (events == null) {
            events = new HashSet<Event>();
        }
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

}
