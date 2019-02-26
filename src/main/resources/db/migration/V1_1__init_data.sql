INSERT INTO "user" (login, password)
VALUES ('user1', 'user1');
INSERT INTO "user" (login, password)
VALUES ('user2', 'user2');
INSERT INTO "user" (login, password)
VALUES ('user3', 'user3');
INSERT INTO "user" (login, password)
VALUES ('user4', 'user4');

INSERT INTO "country" (name)
VALUES ('testCountry');

INSERT INTO "hotel" (name, stars, website, latitude, longitude, feature)
VALUES ('test1', 4, 'test1', 1, 1, 'security');
INSERT INTO "hotel" (name, stars, website, latitude, longitude, feature)
VALUES ('test2', 4, 'test2', 1, 1, 'security');
INSERT INTO "hotel" (name, stars, website, latitude, longitude, feature)
VALUES ('test3', 4, 'test3', 1, 1, 'security');
INSERT INTO "hotel" (name, stars, website, latitude, longitude, feature)
VALUES ('test4', 4, 'test4', 1, 1, 'security');

INSERT INTO "tour" (photo, date, duration, description, cost, tour_type, hotel_id, country_id)
VALUES ('test1.jpg', '12-12-2012', 12, 'description1', 505, 'guided', 1, 1);
INSERT INTO "tour" (photo, date, duration, description, cost, tour_type, hotel_id, country_id)
VALUES ('test2.jpg', '12-12-2012', 12, 'description2', 505, 'guided', 2, 1);
INSERT INTO "tour" (photo, date, duration, description, cost, tour_type, hotel_id, country_id)
VALUES ('test3.jpg', '12-12-2012', 12, 'description3', 505, 'guided', 3, 1);
INSERT INTO "tour" (photo, date, duration, description, cost, tour_type, hotel_id, country_id)
VALUES ('test4.jpg', '12-12-2012', 12, 'description4', 505, 'guided', 4, 1);

INSERT INTO "review" (date, text, user_id, tour_id) VALUES ('12-10-2012', 'Some words1', 1,1);
INSERT INTO "review" (date, text, user_id, tour_id) VALUES ('12-11-2012', 'Some words2', 1,1);
INSERT INTO "review" (date, text, user_id, tour_id) VALUES ('12-12-2012', 'Some words3', 1,1);
INSERT INTO "review" (date, text, user_id, tour_id) VALUES ('12-12-2012', 'Some words4', 1,1);

