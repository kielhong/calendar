CREATE TABLE event (
    id bigint NOT NULL AUTO_INCREMENT,
    title varchar(255),
    description varchar(2000),
    start_date_time timestamp,
    end_date_time timestamp,
    create_date_time timestamp DEFAULT CURRENT_TIMESTAMP,
    modify_date_time timestamp DEFAULT CURRENT_TIMESTAMP,
    primary key (id)
);

CREATE TABLE event_user (
    user_id bigint not null,
    event_id bigint not null,
    primary key (event_id, user_id)
);

CREATE TABLE user (
    id bigint NOT NULL AUTO_INCREMENT,
    email varchar(255),
    create_date_time timestamp DEFAULT CURRENT_TIMESTAMP,
    modify_date_time timestamp DEFAULT CURRENT_TIMESTAMP,
    primary key (id)
);

ALTER TABLE event_user 
    add constraint FK_event_user_event
    foreign key (event_id)
    references event (id);

ALTER TABLE event_user 
    add constraint FK_event_user_user
    foreign key (user_id) 
    references user (id);


INSERT INTO user(id, email) VALUES (1, 'user1@test.com');
INSERT INTO user(id, email) VALUES (2, 'user2@test.com');

INSERT INTO event(id, title, start_date_time, end_date_time) VALUES (1, 'test event', '2016-05-01 13:00:00','2016-05-01 14:00:00');
INSERT INTO event(id, title, start_date_time, end_date_time) VALUES (2, 'test event', '2016-05-03 14:00:00','2016-05-03 15:00:00');
INSERT INTO event(id, title, start_date_time, end_date_time) VALUES (3, 'children day', '2016-05-05 14:00:00','2016-05-05 15:00:00');
INSERT INTO event(id, title, start_date_time, end_date_time) VALUES (4, 'parent day', '2016-05-08 9:00:00','2016-05-8 16:00:00');
INSERT INTO event(id, title, start_date_time, end_date_time) VALUES (5, '서울 랜드', '2016-05-21 10:00:00','2016-05-21 17:00:00');

INSERT INTO event_user(event_id, user_id) VALUES (1, 1);
INSERT INTO event_user(event_id, user_id) VALUES (2, 1);
INSERT INTO event_user(event_id, user_id) VALUES (3, 1);
INSERT INTO event_user(event_id, user_id) VALUES (4, 1);
INSERT INTO event_user(event_id, user_id) VALUES (5, 1);
INSERT INTO event_user(event_id, user_id) VALUES (2, 2);