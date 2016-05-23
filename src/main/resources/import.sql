INSERT INTO user(id, email, create_date_time, modify_date_time) VALUES (1, 'user1@test.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO event(id, title, start_date_time) VALUES (1, 'test event', '2016-05-01 13:00:00');
INSERT INTO event(id, title, start_date_time) VALUES (2, 'test event', '2016-05-03 14:00:00');

INSERT INTO event_user(event_id, user_id) VALUES (1, 1);
INSERT INTO event_user(event_id, user_id) VALUES (2, 1);