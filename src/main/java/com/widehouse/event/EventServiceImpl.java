package com.widehouse.event;

import com.widehouse.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

/**
 * Created by kiel on 2016. 5. 19..
 */
@Service
@Slf4j
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
    public List<Event> listUserEventByBetweenDay(User user, LocalDate beginDate, LocalDate endDate) {
        ZonedDateTime beginDateTime = ZonedDateTime.of(beginDate.atTime(0, 0), ZoneId.systemDefault());
        ZonedDateTime endDateTime = ZonedDateTime.of(endDate.atTime(23, 59), ZoneId.systemDefault());

        return eventRepository.findByUserAndStartDate(user, beginDateTime, endDateTime);
    }

    @Override
    public List<Event> listUserEventByDay(User user, LocalDate currentDate) {
        return listUserEventByBetweenDay(user, currentDate, currentDate);
    }

    @Override
    public List<Event> listUserEventByWeek(User user, LocalDate currentDate) {
        LocalDate firstDayOfWeek = currentDate.with(WeekFields.of(Locale.KOREA).dayOfWeek(), 1);
        LocalDate lastDayOfWeek = firstDayOfWeek.plusDays(6);

        return listUserEventByBetweenDay(user, firstDayOfWeek, lastDayOfWeek);
    }

    @Override
    public List<Event> listUserEventByMonth(User user, LocalDate currentDate) {
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

        return listUserEventByBetweenDay(user, firstDayOfMonth, lastDayOfMonth);
    }
}
