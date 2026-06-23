SET SQL_SAFE_UPDATES=0;

delete from `brs`.`reserve`;

delete from `brs`.`bus`;
ALTER TABLE `brs`.`bus` AUTO_INCREMENT = 200;

INSERT INTO `brs`.`ticket_master` (`username`, `password`) VALUES ('ticket_master','jacliner12345678');

delete from `brs`.`route`;
ALTER TABLE `brs`.`route` AUTO_INCREMENT = 10;

INSERT INTO `brs`.`route` (origin,destination) VALUES ('Ayala MRT',' Balibago Complex');

INSERT INTO `brs`.`route` (origin,destination) VALUES ('Ayala MRT','Binan');

INSERT INTO `brs`.`route` (origin,destination) VALUES ('Ayala MRT','Pacita Complex');


INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (10,1,96,'06:00','09:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (10,0,76,'06:00','09:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (10,1,96,'10:00','13:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (10,0,76,'10:00','13:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (10,1,96,'15:00','18:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (10,0,76,'15:00','18:00');

INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (11,1,78,'06:00','09:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (11,0,58,'06:00','09:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (11,1,78,'10:00','13:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (11,0,58,'10:00','13:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (11,1,78,'15:00','18:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (11,0,58,'15:00','18:00');

INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (12,1,74,'06:00','09:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (12,0,64,'06:00','09:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (12,1,74,'10:00','13:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (12,0,54,'10:00','13:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (12,1,74,'15:00','18:00');
INSERT INTO `brs`.`bus` (route_id,ac,fare,departure_time,arrival_time) VALUES (12,0,54,'15:00','18:00');