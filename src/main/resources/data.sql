PRAGMA foreign_keys = ON;

-- Clear existing data
DELETE FROM reserve;
DELETE FROM bus;
DELETE FROM ticket_master;
DELETE FROM route;

-- Reset AUTOINCREMENT values
DELETE FROM sqlite_sequence WHERE name='reserve';
DELETE FROM sqlite_sequence WHERE name='bus';
DELETE FROM sqlite_sequence WHERE name='ticket_master';
DELETE FROM sqlite_sequence WHERE name='route';

-- Start IDs at the same values as MySQL
INSERT INTO sqlite_sequence(name, seq) VALUES ('route', 9);
INSERT INTO sqlite_sequence(name, seq) VALUES ('bus', 199);
INSERT INTO sqlite_sequence(name, seq) VALUES ('ticket_master', 499);
INSERT INTO sqlite_sequence(name, seq) VALUES ('reserve', 999);

-- Default ticket master
INSERT INTO ticket_master (username, password)
VALUES ('ticket_master', 'jacliner12345678');

-- Routes
INSERT INTO route (origin, destination)
VALUES ('Ayala MRT', 'Balibago Complex');

INSERT INTO route (origin, destination)
VALUES ('Ayala MRT', 'Binan');

INSERT INTO route (origin, destination)
VALUES ('Ayala MRT', 'Pacita Complex');

-- Buses for Route 10
INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (10, 1, 96, '06:00', '09:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (10, 0, 76, '06:00', '09:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (10, 1, 96, '10:00', '13:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (10, 0, 76, '10:00', '13:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (10, 1, 96, '15:00', '18:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (10, 0, 76, '15:00', '18:00');

-- Buses for Route 11
INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (11, 1, 78, '06:00', '09:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (11, 0, 58, '06:00', '09:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (11, 1, 78, '10:00', '13:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (11, 0, 58, '10:00', '13:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (11, 1, 78, '15:00', '18:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (11, 0, 58, '15:00', '18:00');

-- Buses for Route 12
INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (12, 1, 74, '06:00', '09:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (12, 0, 64, '06:00', '09:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (12, 1, 74, '10:00', '13:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (12, 0, 54, '10:00', '13:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (12, 1, 74, '15:00', '18:00');

INSERT INTO bus (route_id, ac, fare, departure_time, arrival_time)
VALUES (12, 0, 54, '15:00', '18:00');