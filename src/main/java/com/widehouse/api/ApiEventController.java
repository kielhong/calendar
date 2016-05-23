package com.widehouse.api;

import com.widehouse.event.Event;
import com.widehouse.event.EventService;
import com.widehouse.user.User;
import com.widehouse.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by kiel on 2016. 5. 23..
 */
@RestController
@Slf4j
public class ApiEventController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventService eventService;

    @RequestMapping("/{userId}/events/day/{day}")
    public List<Event> listDailyEvents(@PathVariable Long userId,
                                       @PathVariable String day) {
        User user = userRepository.findOne(userId);
        LocalDate date = LocalDate.parse(day);

        List<Event> events = eventService.listUserEventByDay(user, date);

        return events;
    }

    @RequestMapping("/{userId}/events/week/{week}")
    public List<Event> listWeeklyEvents(@PathVariable Long userId,
                                       @PathVariable String week) {
        User user = userRepository.findOne(userId);
        LocalDate date = LocalDate.parse(week + "-1", DateTimeFormatter.ISO_WEEK_DATE);

        log.debug("week date :{}", date);

        List<Event> events = eventService.listUserEventByWeek(user, date);

        return events;
    }
}
