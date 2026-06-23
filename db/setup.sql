DROP SCHEMA IF EXISTS `brs` ;

CREATE SCHEMA `brs` ;

CREATE TABLE `brs`.`route` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `origin` VARCHAR(20) NOT NULL,
    `destination` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
  AUTO_INCREMENT=10;
  
CREATE UNIQUE INDEX ROUTE_UNIQUE ON `brs`.`route`(`origin`, `destination`);

CREATE TABLE  `brs`.`bus` (	
	`id` INT NOT NULL AUTO_INCREMENT,
	`route_id` INT NOT NULL,
	`ac` boolean NOT NULL, 
	`fare` int NOT NULL, 
	`departure_time` VARCHAR(6)  NOT NULL,
	`arrival_time` VARCHAR(6) NOT NULL,
	 PRIMARY KEY (`id`), 
	 FOREIGN KEY (`route_id`) REFERENCES  `brs`.`route` (`id`))
     AUTO_INCREMENT=200;
     
CREATE TABLE  `brs`.`ticket_master` (	
	`id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(20) NOT NULL UNIQUE, 
	`password` VARCHAR(20) NOT NULL, 
	-- `name` VARCHAR(40) NOT NULL, 
	-- `email` VARCHAR(32) NOT NULL, 
	-- `mobile` bigint NOT NULL, 
	 PRIMARY KEY (`id`))
     AUTO_INCREMENT = 500;
     
CREATE TABLE  `brs`.`reserve` (	
	`id` INT NOT NULL AUTO_INCREMENT, 
	-- `passengerid` int NOT NULL, 
	`bus_id` int NOT NULL,
	`dt` DATE NOT NULL, 
	`tstamp` DATE NOT NULL, 
	`seat` int NOT NULL, 
    `discounted` boolean NOT NULL,
	 PRIMARY KEY (`id`), 
	--  FOREIGN KEY (`passengerid`) REFERENCES  `brs`.`passenger` (`id`), 
	 FOREIGN KEY (`bus_id`) REFERENCES  `brs`.`bus` (`id`))
	 AUTO_INCREMENT = 1000;
     
CREATE UNIQUE INDEX SEAT_UNIQUE ON `brs`.`reserve`(`bus_id`, `dt`, `seat`);

CREATE VIEW `brs`.`reservation` AS
    SELECT 
        `reserve`.`id` AS `id`,
        -- `reserve`.`passengerid` AS `passengerid`,
        `reserve`.`bus_id` AS `bus_id`,
        `reserve`.`seat` AS `seat`,
        `reserve`.`dt` AS `dt`,
        `reserve`.`timestamp` AS `timestamp`,
        `route`.`origin` AS `origin`,
        `route`.`destination` AS `destination`,
        `bus`.`departure_time` AS `departure_time`,
        `bus`.`arrival_time` AS `arrival_time`,
        `bus`.`fare` AS `fare`, 
        `reserve`.`discounted` AS `discounted`
    FROM
        ((`brs`.`reserve`
        JOIN `brs`.`bus`)
        JOIN `brs`.`route`)
    WHERE
        ((`reserve`.`bus_id` = `bus`.`id`)
            AND (`route`.`id` = `bus`.`route_id`))
    ORDER BY `reserve`.`id`;