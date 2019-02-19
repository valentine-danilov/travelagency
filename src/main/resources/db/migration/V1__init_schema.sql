CREATE TYPE TOUR_TYPE AS ENUM
  (
    'bus', 'sightseeing', 'shore_excursion',
    'adventure', 'sporting', 'cruise',
    'escorted', 'guided', 'rail', 'extreme'
    );

CREATE TYPE HOTEL_FEATURES AS ENUM
  (
    'cleanliness', 'security', 'wi-fi', 'breakfast', 'parking', 'air_conditioning',
    'food_delivery', 'pool', 'conference_hall', 'safe'
    );



CREATE TABLE IF NOT EXISTS "user"
(
  id       SERIAL,
  login    VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255)        NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "hotel"
(
  id        SERIAL,
  name      VARCHAR(255)   NOT NULL,
  stars     SMALLINT       NOT NULL,
  website   VARCHAR(255)   NOT NULL,
  latitude  DECIMAL(8, 6)  NOT NULL,
  longitude DECIMAL(8, 6)  NOT NULL,
  feature   HOTEL_FEATURES NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "country"
(
  id   SERIAL,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "tour"
(
  id          SERIAL,
  photo       VARCHAR(255),
  date        DATE           NOT NULL,
  duration    INT            NOT NULL,
  description TEXT           NOT NULL,
  cost        DECIMAL(19, 3) NOT NULL,
  tour_type   TOUR_TYPE      NOT NULL,
  hotel_id    INT            NOT NULL,
  country_id  INT            NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (hotel_id) REFERENCES "hotel" (id),
  FOREIGN KEY (country_id) REFERENCES "country" (id)
);

CREATE TABLE IF NOT EXISTS "usertour"
(
  user_id INT UNIQUE NOT NULL,
  tour_id INT UNIQUE NOT NULL,
  PRIMARY KEY (user_id, tour_id),
  FOREIGN KEY (user_id) REFERENCES "user" (id),
  FOREIGN KEY (tour_id) REFERENCES "tour" (id)
);

CREATE TABLE IF NOT EXISTS "review"
(
  id      SERIAL,
  date    DATE NOT NULL,
  text    TEXT NOT NULL,
  user_id INT  NOT NULL,
  tour_id INT  NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES "user" (id),
  FOREIGN KEY (tour_id) REFERENCES "tour" (id)
)



