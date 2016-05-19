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

    @Before
    public void setup() {
        user = new User();
        userRepository.save(user);
    }

    @Test
    public void createEventShouldCreateEventAndUserMutual() {
        // Given
        Event eventDto = new Event();
        eventDto.setStartDateTime(LocalDateTime.of(2016, 5, 1, 10, 30));
        // When
        Event event = eventService.createEvent(user, eventDto);
        // Then
        assertThat(event.getUsers().size()).isEqualTo(1);
        assertThat(event.getUsers()).contains(user);
        assertThat(event.getStartDateTime()).isEqualTo(LocalDateTime.of(2016, 5, 1, 10, 30));
        assertThat(user.getEvents()).contains(event);
    }

    @Test
    public void listUserEventByDayShouldListSameDayEventsOfUser() {
        // Given
        eventService = new EventServiceImpl(eventRepository);
        Event eventDto1 = new Event();
        eventDto1.setStartDateTime(LocalDateTime.of(2016, 5, 1, 10, 30));
        Event eventDto2 = new Event();
        eventDto2.setStartDateTime(LocalDateTime.of(2016, 5, 1, 12, 00));
        eventService.createEvent(user, eventDto1);
        eventService.createEvent(user, eventDto2);
        given(eventRepository.findByUserAndStartDate(user,
                LocalDateTime.of(2016, 5, 1, 0, 0),
                LocalDateTime.of(2016, 5, 2, 0, 0)))
                .willReturn(Arrays.asList(eventDto1, eventDto2));
        // When
        List<Event> events = eventService.listUserEventByDay(user, LocalDate.of(2016, 5, 1));
        // Then
        assertThat(events).isEqualTo(Arrays.asList(eventDto1, eventDto2));
    }
}
