package com.widehouse.user;


import static org.assertj.core.api.Assertions.assertThat;

import com.widehouse.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by kiel on 2016. 5. 18..
 */
@RunWith(SpringRunner.class)
public class UserTest {
    @Test
    public void userAddEvent() {
        User user = new User();

        user.addEvent(new Event());

        assertThat(user.getEvents().size()).isEqualTo(1);
    }

    @Test
    public void userRemoveEvent() {
        User user = new User();
        Event event = new Event();
        user.addEvent(event);

        user.removeEvent(event);

        assertThat(user.getEvents().size()).isEqualTo(0);
    }
}
