DROP TABLE IF EXISTS player_avatar;
DROP TABLE IF EXISTS avatar;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS location;

CREATE TABLE location (
  location_id int NOT NULL AUTO_INCREMENT,
  region_name varchar(272) NOT NULL,
  city_area varchar(137) NOT NULL,
  housing varchar(56),
  PRIMARY KEY (location_id)
);

CREATE TABLE avatar (
	avatar_id int NOT NULL AUTO_INCREMENT,
	location_id int NULL,
	name varchar(100) NOT NULL,
	race varchar(120) NOT NULL,
	age int,
	profession varchar(110) NOT NULL,
	weapon varchar(50),
	PRIMARY KEY(avatar_id),
	FOREIGN KEY (location_id) REFERENCES location (location_id) ON DELETE CASCADE
);

CREATE TABLE player (
	player_id int NOT NULL AUTO_INCREMENT,
	name varchar(40),
	avatar_name varchar(100),
	PRIMARY KEY(player_id)
);

CREATE TABLE player_avatar (
	avatar_id int NOT NULL,
	player_id int NOT NULL,
	FOREIGN KEY (avatar_id) REFERENCES avatar (avatar_id) ON DELETE CASCADE,
	FOREIGN KEY (player_id) REFERENCES player (player_id) ON DELETE CASCADE
);