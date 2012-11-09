-- Buddy Age Person table setup and (re-)initialization.

DROP TABLE IF EXISTS BuddyAge7_Person;

CREATE TABLE BuddyAge7_Person (
	id BIGINT NOT NULL,
	version BIGINT NOT NULL,
	name VARCHAR(64) NOT NULL, 
	age INT NOT NULL,
	buddy BIGINT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT into BuddyAge7_Person VALUE
	(1, 1, "Alice7", 24, 2),
	(2, 1, "Bob7", 26, 3),
	(3, 1, "Carl7", 21, null),
	(4, 1, "Doug7", 28, null);

