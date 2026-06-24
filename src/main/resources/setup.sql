-- Drop existing objects
DROP VIEW IF EXISTS reservation;
DROP TABLE IF EXISTS reserve;
DROP TABLE IF EXISTS ticket_master;
DROP TABLE IF EXISTS bus;
DROP TABLE IF EXISTS route;

PRAGMA foreign_keys = ON;

CREATE TABLE route (
                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                       origin TEXT NOT NULL,
                       destination TEXT NOT NULL
);

CREATE UNIQUE INDEX route_unique
    ON route(origin, destination);

CREATE TABLE bus (
                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                     route_id INTEGER NOT NULL,
                     ac INTEGER NOT NULL,
                     fare INTEGER NOT NULL,
                     departure_time TEXT NOT NULL,
                     arrival_time TEXT NOT NULL,

                     FOREIGN KEY (route_id) REFERENCES route(id)
);

CREATE TABLE ticket_master (
                               id INTEGER PRIMARY KEY AUTOINCREMENT,
                               username TEXT NOT NULL UNIQUE,
                               password TEXT NOT NULL
);

CREATE TABLE reserve (
                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                         bus_id INTEGER NOT NULL,
                         dt DATE NOT NULL,
                         tstamp DATE NOT NULL,
                         seat INTEGER NOT NULL,
                         discounted INTEGER NOT NULL,

                         FOREIGN KEY (bus_id) REFERENCES bus(id)
);

CREATE UNIQUE INDEX seat_unique
    ON reserve(bus_id, dt, seat);

CREATE VIEW reservation AS
SELECT
    reserve.id,
    reserve.bus_id,
    reserve.seat,
    reserve.dt,
    reserve.tstamp,
    route.origin,
    route.destination,
    bus.departure_time,
    bus.arrival_time,
    bus.fare,
    reserve.discounted
FROM reserve
         JOIN bus
              ON reserve.bus_id = bus.id
         JOIN route
              ON bus.route_id = route.id
ORDER BY reserve.id;