DROP TABLE IF EXISTS users;

CREATE TABLE users (
	id INT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(10) NOT NULL,
	email VARCHAR(20),
	password VARCHAR(20)
);

INSERT INTO users(name, email, password) VALUES('testName', 'testEmail@test.com', 'testPassword');