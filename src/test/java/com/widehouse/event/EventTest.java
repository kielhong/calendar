package com.widehouse.event;

import static org.assertj.core.api.Assertions.assertThat;

import com.widehouse.user.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by kiel on 2016. 5. 18..
 */
@RunWith(SpringRunner.class)
public class EventTest {
    @Test
    public void eventAddUser() {
        Event event = new Event();

        event.addUser(new User());

        assertThat(event.getUsers().size()).isEqualTo(1);
    }

    @Test
    public void startDateTimeShouldSetStartDateAndStartTime() {
        Event event = new Event();
        LocalDateTime startDateTime = LocalDateTime.of(2016, 5, 10, 15, 30);
        event.setStartDateTime(startDateTime.atZone(ZoneId.systemDefault()));

        assertThat(event.getStartDateTime())
                .isGreaterThanOrEqualTo(LocalDateTime.of(2016, 5, 10, 0, 0).atZone(ZoneId.systemDefault()))
                .isLessThan(LocalDateTime.of(2016, 5, 11, 0, 0).atZone(ZoneId.systemDefault()));
    }
}
