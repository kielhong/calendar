package com.widehouse.event;

import com.widehouse.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Created by kiel on 2016. 5. 18..
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString(exclude = {"users"})
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    private String title;

    @Column(length = 2000)
    private String description;

    private ZonedDateTime startDateTime;

    private ZonedDateTime endDateTime;

    @CreatedDate
    private ZonedDateTime createDateTime;

    @LastModifiedDate
    private ZonedDateTime modifyDateTime;

    /**
     * add user to event
     * @param user User
     */
    public void addUser(User user) {
        if (users == null) {
            users = new HashSet<User>();
        }

        users.add(user);
    }
}
