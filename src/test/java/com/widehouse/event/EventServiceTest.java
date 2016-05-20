package com.widehouse.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.widehouse.CalendarApplication;
import com.widehouse.user.User;
import com.widehouse.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kiel on 2016. 5. 19..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CalendarApplication.class})
@Slf4j
public class EventServiceTest {
    @MockBean
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventService eventService;

    User user;

    Event event1;
    Event event2;
    Event event3;
    Event event4;
    Event event5;
    Event event6;
    Event event7;

    /**
     * test setup
     */
    @Before
    public void setup() {
        user = new User();
        userRepository.save(user);

        eventService = new EventServiceImpl(eventRepository);
        event1 = new Event();
        event1.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 1, 10, 30), ZoneId.systemDefault()));
        eventService.createEvent(user, event1);
        event2 = new Event();
        event2.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 1, 12, 0), ZoneId.systemDefault()));
        eventService.createEvent(user, event2);
        event3 = new Event();
        event3.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 4, 9, 0), ZoneId.systemDefault()));
        eventService.createEvent(user, event3);
        event4 = new Event();
        event4.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 7, 20, 00), ZoneId.systemDefault()));
        eventService.createEvent(user, event4);
        event5 = new Event();
        event5.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 8, 0, 30), ZoneId.systemDefault()));
        eventService.createEvent(user, event5);
        event6 = new Event();
        event6.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 31, 23, 59), ZoneId.systemDefault()));
        eventService.createEvent(user, event6);
        event7 = new Event();
        event7.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 6, 2, 0, 30), ZoneId.systemDefault()));
        eventService.createEvent(user, event7);
    }

    @Test
    public void createEventShouldCreateEventAndUserMutual() {
        // Given
        Event eventDto = new Event();
        eventDto.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 1, 10, 30), ZoneId.systemDefault()));
        // When
        Event event = eventService.createEvent(user, eventDto);
        // Then
        assertThat(event.getUsers().size()).isEqualTo(1);
        assertThat(event.getUsers()).contains(user);
        assertThat(event.getStartDateTime())
                .isEqualTo(LocalDateTime.of(2016, 5, 1, 10, 30).atZone(ZoneId.systemDefault()));
        assertThat(user.getEvents()).contains(event);
    }


    @Test
    public void listUserEventByBetweenDayShouldListEventBetweenTwodays() {
        // Given
        given(eventRepository.findByUserAndStartDate(user,
                ZonedDateTime.of(LocalDateTime.of(2016, 5, 1, 0, 0), ZoneId.systemDefault()),
                ZonedDateTime.of(LocalDateTime.of(2016, 5, 4, 23, 59), ZoneId.systemDefault())))
                .willReturn((Arrays.asList(event1, event2, event3)));
        // When
        List<Event> events =
                eventService.listUserEventByBetweenDay(user, LocalDate.of(2016, 5, 1), LocalDate.of(2016, 5, 4));
        // then
        assertThat(events).isEqualTo(Arrays.asList(event1, event2, event3));
    }

    @Test
    public void listUserEventByDayShouldListSameDayEventsOfUser() {
        // Given
        given(eventRepository.findByUserAndStartDate(user,
                ZonedDateTime.of(LocalDateTime.of(2016, 5, 1, 0, 0), ZoneId.systemDefault()),
                ZonedDateTime.of(LocalDateTime.of(2016, 5, 1, 23, 59), ZoneId.systemDefault())))
                .willReturn(Arrays.asList(event1, event2));
        // When
        List<Event> events = eventService.listUserEventByDay(user, LocalDate.of(2016, 5, 1));
        // Then
        assertThat(events).isEqualTo(Arrays.asList(event1, event2));
    }

    @Test
    public void listUserEventByWeekShouldListSameWeekEvents() {
        // Given
        given(eventRepository.findByUserAndStartDate(user,
                ZonedDateTime.of(LocalDateTime.of(2016, 5, 1, 0, 0), ZoneId.systemDefault()),
                ZonedDateTime.of(LocalDateTime.of(2016, 5, 7, 23, 59), ZoneId.systemDefault())))
                .willReturn((Arrays.asList(event1, event2, event3, event4)));
        // When
        List<Event> events = eventService.listUserEventByWeek(user, LocalDate.of(2016, 5, 5));
        // then
        assertThat(events).isEqualTo(Arrays.asList(event1, event2, event3, event4));
    }

    @Test
    public void listUserEventByMonthShouldListSameMonthEvents() {
        // Given
        given(eventRepository.findByUserAndStartDate(user,
                ZonedDateTime.of(LocalDateTime.of(2016, 5, 1, 0, 0), ZoneId.systemDefault()),
                ZonedDateTime.of(LocalDateTime.of(2016, 5, 31, 23, 59), ZoneId.systemDefault())))
                .willReturn((Arrays.asList(event1, event2, event3, event4, event5, event6)));
        // When
        List<Event> events = eventService.listUserEventByMonth(user, LocalDate.of(2016, 5, 11));
        // then
        assertThat(events).isEqualTo(Arrays.asList(event1, event2, event3, event4, event5, event6));
    }
}
