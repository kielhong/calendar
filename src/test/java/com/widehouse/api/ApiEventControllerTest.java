package com.widehouse.api;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import com.widehouse.event.Event;
import com.widehouse.event.EventService;
import com.widehouse.user.User;
import com.widehouse.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

/**
 * Created by kiel on 2016. 5. 23..
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ApiEventController.class)
public class ApiEventControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserRepository userRepository;
    @MockBean
    EventService eventService;

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
        User user = new User();
        user.setId(1L);

        event1 = new Event();
        event1.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 1, 10, 30), ZoneId.systemDefault()));
        event2 = new Event();
        event2.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 1, 12, 0), ZoneId.systemDefault()));
        event3 = new Event();
        event3.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 4, 9, 0), ZoneId.systemDefault()));
        event4 = new Event();
        event4.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 7, 20, 00), ZoneId.systemDefault()));
        event5 = new Event();
        event5.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 8, 0, 30), ZoneId.systemDefault()));
        event6 = new Event();
        event6.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 5, 31, 23, 59), ZoneId.systemDefault()));
        event7 = new Event();
        event7.setStartDateTime(ZonedDateTime.of(LocalDateTime.of(2016, 6, 2, 0, 30), ZoneId.systemDefault()));
    }

    @Test
    public void testDailyEventList() throws Exception {
        User user = new User();
        user.setId(1L);
        given(userRepository.findOne(user.getId())).willReturn(user);
        given(eventService.listUserEventByDay(user, LocalDate.of(2016, 5, 1))).willReturn(Arrays.asList(event1, event2));

        // When, Then
        mockMvc.perform(get("/{userId}/events/day/{day}", user.getId(), "2016-05-01"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ApiEventController.class))
                .andExpect(handler().methodName("listDailyEvents"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }

    @Test
    public void testWeeklyEventList() throws Exception {
        User user = new User();
        user.setId(1L);
        given(userRepository.findOne(user.getId())).willReturn(user);
        given(eventService.listUserEventByWeek(user, LocalDate.of(2016, 5, 2))).willReturn(Arrays.asList(event1, event2, event3, event4));

        // When, Then
        mockMvc.perform(get("/{userId}/events/week/{week}", user.getId(), "2016-w18"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ApiEventController.class))
                .andExpect(handler().methodName("listWeeklyEvents"))
                .andExpect(jsonPath("$", hasSize(4)))
                .andDo(print());
    }
}
