package com.widehouse.event;

import com.widehouse.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by kiel on 2016. 5. 19..
 */
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT event FROM Event event "
            + " WHERE :user MEMBER OF event.users AND event.startDateTime BETWEEN :begin AND :end")
    List<Event> findByUserAndStartDate(@Param("user") User user,
                                       @Param("begin") ZonedDateTime beginStartDateTime,
                                       @Param("end") ZonedDateTime endStartDateTime);
}
