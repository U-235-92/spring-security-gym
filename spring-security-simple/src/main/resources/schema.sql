CREATE TABLE spring_users 
( 	
	id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL, 
	password VARCHAR(45) NOT NULL, 
	enabled INT NOT NULL, 
	PRIMARY KEY (id)
);

CREATE TABLE spring_authorities 
(
	id INT NOT NULL AUTO_INCREMENT, 
	username VARCHAR(45) NOT NULL, 
	authority VARCHAR(45) NOT NULL, 
	PRIMARY KEY (id)
);

CREATE TABLE spring_csrf_tokens 
( 
	id INT NOT NULL AUTO_INCREMENT, 
	identifier VARCHAR(45) NULL, 
	token TEXT NULL, PRIMARY KEY (id)
);