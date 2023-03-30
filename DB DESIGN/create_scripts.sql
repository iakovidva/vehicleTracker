DROP SCHEMA IF EXISTS vehicle_tracker;
CREATE SCHEMA vehicle_tracker;
USE vehicle_tracker;

DROP TABLE IF EXISTS mechanic;
CREATE TABLE mechanic(
	mechanic_id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(45) NOT NULL,
	last_name VARCHAR(45) NOT NULL,
	email VARCHAR(65) NOT NULL,
	phone_number VARCHAR(15) NOT NULL,
	password VARCHAR(100) NOT NULL,
	workshop_name VARCHAR(45) DEFAULT NULL,
	specialisation VARCHAR(500) DEFAULT NULL,
	PRIMARY KEY (mechanic_id)
);

DROP TABLE IF EXISTS owner;
CREATE TABLE owner(
	owner_id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(45) NOT NULL,
	last_name VARCHAR(45) NOT NULL,
	email VARCHAR(65) NOT NULL,
	phone_number VARCHAR(15) NOT NULL,
	password VARCHAR(100) NOT NULL,
	PRIMARY KEY (owner_id)
);

DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle(
	vehicle_id INT NOT NULL AUTO_INCREMENT,
	make VARCHAR(45) NOT NULL,
	model VARCHAR(45) NOT NULL,
	license_plate VARCHAR(10) NOT NULL,
	production_date VARCHAR(7) DEFAULT NULL,
	owner_id INT NOT NULL,
	mechanic_id INT DEFAULT NULL,
	dt_eff DATE NOT NULL,
	dt_end DATE NOT NULL DEFAULT '9999-12-31',
	PRIMARY KEY (vehicle_id),
	CONSTRAINT FK_OWNER_VEHICLE
		FOREIGN KEY (owner_id) REFERENCES owner(owner_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT FK_MECHANIC_VEHICLE
		FOREIGN KEY (mechanic_id) REFERENCES mechanic(mechanic_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS service_book;
CREATE TABLE service_book(
	service_book_id INT NOT NULL AUTO_INCREMENT,
	vehicle_id INT NOT NULL,
	mechanic_id INT NOT NULL,
	activity VARCHAR(100) NOT NULL,
	comments VARCHAR(300) DEFAULT NULL,
	cost DECIMAL(10,2) DEFAULT NULL,
	kilometers INT NOT NULL,
	dt_rec DATE NOT NULL,
	PRIMARY KEY (service_book_id),
	CONSTRAINT FK_SERVICE_BOOK_VEHICLE
		FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FK_SERVICE_BOOK_MECHANIC
		FOREIGN KEY (mechanic_id) REFERENCES mechanic(mechanic_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS daily_activity;
CREATE TABLE daily_activity(
	daily_activity_id INT NOT NULL AUTO_INCREMENT,
	vehicle_id INT NOT NULL,
	activity VARCHAR(100) NOT NULL,
	comments VARCHAR(300) DEFAULT NULL,
	cost DECIMAL(10,2) DEFAULT NULL,
	kilometers INT NOT NULL,
	dt_rec DATE NOT NULL,
	PRIMARY KEY (daily_activity_id),
	CONSTRAINT FK_DAILY_ACTIVITY_VEHICLE
		FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS mechanic_review;
CREATE TABLE mechanic_review(
	review_id INT NOT NULL AUTO_INCREMENT,
	rating DECIMAL(3,1) CHECK (rating>=0 AND rating<=10) NOT NULL,
	comment VARCHAR(150) DEFAULT NULL,
	dt_rec DATE NOT NULL,
	mechanic_id INT NOT NULL,
	owner_id INT NOT NULL,
	vehicle_id INT NOT NULL,
	PRIMARY KEY (review_id),
	CONSTRAINT FK_MECHANIC_REVIEW
		FOREIGN KEY (mechanic_id) REFERENCES mechanic(mechanic_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT FK_OWNER_REVIEW
		FOREIGN KEY (owner_id) REFERENCES owner(owner_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FK_VEHICLE_REVIEW
		FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS mechanic_schedule;
CREATE TABLE mechanic_schedule(
	mechanic_id INT NOT NULL,
	appointment_date DATE NOT NULL,
	start_time TIME NOT NULL,
	end_time TIME NOT NULL,
	is_available TINYINT(1) DEFAULT 0,
	PRIMARY KEY (mechanic_id, appointment_date, start_time, end_time),
	CONSTRAINT FK_MECHANIC_SCHEDULE
		FOREIGN KEY (mechanic_id) REFERENCES mechanic(mechanic_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS mechanic_location;
CREATE TABLE mechanic_location(
	mechanic_location_id INT NOT NULL AUTO_INCREMENT,
	mechanic_id INT NOT NULL,
	city VARCHAR(50) DEFAULT NULL,
	postal_code INT DEFAULT NULL,
	street_name VARCHAR(50) DEFAULT NULL,
	street_number INT DEFAULT NULL,
	coordinates VARCHAR(50) DEFAULT NULL,
	PRIMARY KEY (mechanic_location_id, mechanic_id),
	CONSTRAINT FK_MECHANIC_LOCATION
		FOREIGN KEY (mechanic_id) REFERENCES mechanic(mechanic_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION
);