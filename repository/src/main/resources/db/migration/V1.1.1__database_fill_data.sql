INSERT INTO travelagency."user" (login, password)
VALUES ('user1', 'user1');
INSERT INTO travelagency."user" (login, password)
VALUES ('user2', 'user2');
INSERT INTO travelagency."user" (login, password)
VALUES ('user3', 'user3');
INSERT INTO travelagency."user" (login, password)
VALUES ('user4', 'user4');

INSERT INTO travelagency."country" (name)
VALUES ('testCountry');

INSERT INTO travelagency."country" (name)
VALUES ('testCountry2');

INSERT INTO travelagency."hotel" (name, stars, website, latitude, longitude, feature)
VALUES ('test1', 3, 'test1', 1, 1, 'SECURITY');
INSERT INTO travelagency."hotel" (name, stars, website, latitude, longitude, feature)
VALUES ('test2', 5, 'test2', 1, 1, 'SECURITY');
INSERT INTO travelagency."hotel" (name, stars, website, latitude, longitude, feature)
VALUES ('test3', 5, 'test3', 1, 1, 'SECURITY');
INSERT INTO travelagency."hotel" (name, stars, website, latitude, longitude, feature)
VALUES ('test4', 3, 'test4', 1, 1, 'SECURITY');

INSERT INTO travelagency."tour" (photo, date, duration, description, cost, tour_type, hotel_id, country_id)
VALUES ('test1.jpg', '12-12-2012', 13, 'description1', 505, 'GUIDED', 2, 1);
INSERT INTO travelagency."tour" (photo, date, duration, description, cost, tour_type, hotel_id, country_id)
VALUES ('test2.jpg', '11-12-2012', 10, 'description2', 500, 'GUIDED', 2, 1);
INSERT INTO travelagency."tour" (photo, date, duration, description, cost, tour_type, hotel_id, country_id)
VALUES ('test3.jpg', '12-12-2012', 11, 'description3', 500, 'BUS', 3, 2);
INSERT INTO travelagency."tour" (photo, date, duration, description, cost, tour_type, hotel_id, country_id)
VALUES ('test4.jpg', '12-12-2012', 12, 'description4', 505, 'GUIDED', 4, 2);

INSERT INTO travelagency."review" (date, text, user_id, tour_id)
VALUES ('12-12-2012', 'Some words1', 1, 2);
INSERT INTO travelagency."review" (date, text, user_id, tour_id)
VALUES ('12-11-2012', 'Some words2', 2, 1);
INSERT INTO travelagency."review" (date, text, user_id, tour_id)
VALUES ('12-11-2012', 'Some words3', 1, 2);
INSERT INTO travelagency."review" (date, text, user_id, tour_id)
VALUES ('12-12-2012', 'Some words4', 4, 1);

INSERT INTO travelagency.usertour (user_id, tour_id)
VALUES (2, 2);
INSERT INTO travelagency.usertour (user_id, tour_id)
VALUES (3, 3);
INSERT INTO travelagency.usertour (user_id, tour_id)
VALUES (1, 2);
INSERT INTO travelagency.usertour (user_id, tour_id)
VALUES (2, 4);


