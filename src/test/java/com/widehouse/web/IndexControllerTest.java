package com.widehouse.web;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.widehouse.api.ApiEventController;
import com.widehouse.event.Event;
import com.widehouse.event.EventService;
import com.widehouse.user.User;
import com.widehouse.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kiel on 2016. 6. 7..
 */
@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserRepository userRepository;
    @MockBean
    EventService eventService;
    @InjectMocks
    IndexController indexController;

    @Test
    public void userCalendar_calendarByUser() throws Exception {
        Long userId = 1L;
        User user = new User();
        given(userRepository.findOne(userId))
                .willReturn(user);
        given(eventService.listUserEventByMonth(user, LocalDate.of(2016,5,1)))
                .willReturn(Arrays.asList(new Event()));

        // Expected
        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isInternalServerError())  // DomainClassConverter needs ApplicationContext. MockMvc not use ApplicationContext
                .andExpect(handler().handlerType(IndexController.class))
                .andExpect(handler().methodName("userCalendar"))
                .andDo(print());

    }
}
