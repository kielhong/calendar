package com.widehouse.event;

import com.widehouse.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;
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

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime startDateTime;

    @CreatedDate
    private LocalDateTime createDateTime;

    @LastModifiedDate
    private LocalDateTime modifyDateTime;

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

    @Converter(autoApply = true)
    public static class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
        @Override
        public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
            return dbData == null ? null : dbData.toLocalDateTime();
        }

        @Override
        public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
            return attribute == null ? null : Timestamp.valueOf(attribute);
        }
    }

}
