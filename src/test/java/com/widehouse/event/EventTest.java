package com.widehouse.event;

import static org.assertj.core.api.Assertions.assertThat;

import com.widehouse.user.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void addUserShouldUserHasEvent() {
        Event event = new Event();
        User user = new User();

        event.addUser(user);

        assertThat(user.getEvents().size()).isEqualTo(1);
    }
}
