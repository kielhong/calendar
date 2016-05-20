package com.widehouse.event;

import static org.assertj.core.api.Assertions.assertThat;

import com.widehouse.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
@DataJpaTest
@Slf4j
public class EventRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    EventRepository eventRepository;

    private User user;
    private Event event1;
    private Event event2;
    private Event event3;
    private Event event4;

    /**
     * set up data
     */
    @Before
    public void setup() {
        user = new User();
        entityManager.persist(user);

        event1 = new Event();
        event1.addUser(user);
        event1.setStartDateTime(LocalDateTime.of(2016, 5, 1, 9, 30).atZone(ZoneId.systemDefault()));
        entityManager.persist(event1);

        event2 = new Event();
        event2.addUser(user);
        event2.setStartDateTime(LocalDateTime.of(2016, 5, 2, 15, 0).atZone(ZoneId.systemDefault()));
        entityManager.persist(event2);

        event3 = new Event();
        event3.addUser(user);
        event3.setStartDateTime(LocalDateTime.of(2016, 5, 4, 11, 0).atZone(ZoneId.systemDefault()));
        entityManager.persist(event3);

        event4 = new Event();
        event4.addUser(user);
        event4.setStartDateTime(LocalDateTime.of(2016, 5, 7, 20, 0).atZone(ZoneId.systemDefault()));
        entityManager.persist(event4);
    }

    @Test
    public void testFindByUserAndDate() {
        // When
        LocalDate date = LocalDate.of(2016, 5, 1);
        List<Event> events = eventRepository.findByUserAndStartDate(user,
                ZonedDateTime.of(date.atTime(0, 0), ZoneId.systemDefault()),
                ZonedDateTime.of(date.atTime(23, 59), ZoneId.systemDefault()));
        // Then
        assertThat(events.size()).isEqualTo(1);
        assertThat(events).isEqualTo(Arrays.asList(event1));
    }

    @Test
    public void testFindByUserAndBeginStartDateAndEndStartDate() {
        // When
        ZonedDateTime beginDateTime = LocalDateTime.of(2016, 5, 1, 0, 0).atZone(ZoneId.systemDefault());
        ZonedDateTime endDateTime = LocalDateTime.of(2016, 5, 7, 23, 59).atZone(ZoneId.systemDefault());
        List<Event> events =
                eventRepository.findByUserAndStartDate(user, beginDateTime, endDateTime);
        // Then
        assertThat(events).isEqualTo(Arrays.asList(event1, event2, event3, event4));
    }
}