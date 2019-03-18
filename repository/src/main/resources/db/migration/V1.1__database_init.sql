
CREATE TYPE travelagency.tourtype AS ENUM
  (
    'BUS', 'SIGHTSEEING', 'SHORE_EXCURSION',
    'ADVENTURE', 'SPORTING', 'CRUISE',
    'ESCORTED', 'GUIDED', 'RAIL', 'EXTREME'
    );

CREATE TYPE travelagency.hotelfeature AS ENUM
  (
    'CLEANLINESS', 'SECURITY', 'WI_FI', 'BREAKFAST', 'PARKING', 'AIR_CONDITIONING',
    'FOOD_DELIVERY', 'POOL', 'CONFERENCE_HALL', 'SAFE'
    );

CREATE TYPE travelagency."role" AS ENUM
  (
    'ADMIN', 'USER'
    );

CREATE TABLE IF NOT EXISTS travelagency."user"
(
  id       SERIAL,
  login    VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255)        NOT NULL,
  role     travelagency."role"              NOT NULL DEFAULT 'USER',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS travelagency.hotel
(
  id        SERIAL        NOT NULL,
  name      VARCHAR(255)  NOT NULL,
  stars     SMALLINT      NOT NULL,
  website   VARCHAR(255)  NOT NULL,
  latitude  DECIMAL(8, 6) NOT NULL,
  longitude DECIMAL(8, 6) NOT NULL,
  feature   travelagency.hotelfeature  NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS travelagency.country
(
  id   SERIAL NOT NULL,
  name VARCHAR(255) UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS travelagency.tour
(
  id          SERIAL         NOT NULL,
  photo       VARCHAR(255),
  date        DATE           NOT NULL,
  duration    INT            NOT NULL,
  description TEXT           NOT NULL,
  cost        DECIMAL(19, 3) NOT NULL,
  tour_type   travelagency.tourtype       NOT NULL,
  hotel_id    INT            NOT NULL,
  country_id  INT            NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (hotel_id) REFERENCES travelagency.hotel (id)
    ON UPDATE CASCADE,
  FOREIGN KEY (country_id) REFERENCES travelagency.country (id)
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS travelagency.usertour
(
  user_id INT NOT NULL,
  tour_id INT NOT NULL,
  PRIMARY KEY (user_id, tour_id),
  FOREIGN KEY (user_id) REFERENCES travelagency."user" (id)
    ON UPDATE CASCADE,
  FOREIGN KEY (tour_id) REFERENCES travelagency.tour (id)
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS travelagency.review
(
  id      SERIAL NOT NULL,
  date    DATE   NOT NULL,
  text    TEXT   NOT NULL,
  user_id INT    NOT NULL,
  tour_id INT    NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES travelagency."user" (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  FOREIGN KEY (tour_id) REFERENCES travelagency.tour (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)



