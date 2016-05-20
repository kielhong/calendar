package com.widehouse.event;

import com.widehouse.user.User;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.List;

/**
 * Created by kiel on 2016. 5. 19..
 */
public interface EventService {
    Event createEvent(User user, Event eventDto);

    List<Event> listUserEventByBetweenDay(User user, LocalDate startDate, LocalDate endDate);

    List<Event> listUserEventByDay(User user, LocalDate date);

    List<Event> listUserEventByWeek(User user, LocalDate firstDayOfWeek);

    List<Event> listUserEventByMonth(User user, LocalDate currentDate);

}
