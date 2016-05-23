package com.widehouse.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.widehouse.event.Event;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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
@ToString(exclude = "events")
public class User {
    @Id
    @GeneratedValue
    Long id;

    String email;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "event_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    Set<Event> events;

    @CreatedDate
    private ZonedDateTime createDateTime;

    @LastModifiedDate
    private ZonedDateTime modifyDateTime;

    /**
     * add event to user
     * @param event Event
     */
    public void addEvent(Event event) {
        if (events == null) {
            events = new HashSet<Event>();
        }
        events.add(event);
    }

    /**
     * remove event from user
     * @param event Event
     */
    public void removeEvent(Event event) {
        events.remove(event);
    }
}
