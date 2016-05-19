package com.widehouse.event;

import com.widehouse.user.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by kiel on 2016. 5. 19..
 */
public interface EventService {
    Event createEvent(User user, Event eventDto);

    List<Event> listUserEventByDay(User user, LocalDate date);
}
