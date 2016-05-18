package com.widehouse.event;

import com.widehouse.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kiel on 2016. 5. 18..
 */
@Getter
@Setter
public class Event {
    Long id;

    Set<User> users;

    public void addUser(User user) {
        if (users == null) {
            users = new HashSet<User>();
        }

        users.add(user);

        user.addEvent(this);
    }
}
