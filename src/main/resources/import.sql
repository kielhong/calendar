INSERT INTO user(id, email, create_date_time, modify_date_time) VALUES (1, 'user1@test.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user(id, email, create_date_time, modify_date_time) VALUES (2, 'user2@test.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

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