package com.widehouse.event;

import com.widehouse.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.List;

/**
 * Created by kiel on 2016. 5. 19..
 */
@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(User user, Event eventDto) {
        Event event = new Event();
        event.setStartDateTime(eventDto.getStartDateTime());
        event.addUser(user);

        user.addEvent(event);

        return event;
    }

    @Override
    public List<Event> listUserEventByBetweenDay(User user, LocalDate startDate, LocalDate endDate) {
        return eventRepository.findByUserAndStartDate(user, startDate.atTime(0, 0), endDate.atTime(23, 59));
    }

    @Override
    public List<Event> listUserEventByDay(User user, LocalDate date) {
        return listUserEventByBetweenDay(user, date, date);
    }

    @Override
    public List<Event> listUserEventByWeek(User user, LocalDate firstDayOfWeek) {
        return listUserEventByBetweenDay(user, firstDayOfWeek, firstDayOfWeek.plusDays(6));
    }
}
