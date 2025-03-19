DROP DATABASE if exists RESU;
CREATE DATABASE RESU;
USE RESU;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password) VALUES
('admin', 'password123'),
('john_doe', 'securepass')
('james', '');

DROP USER 'RESU-ADMIN'@'localhost';
CREATE USER 'RESU-ADMIN'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON RESU.* TO 'RESU-ADMIN'@'localhost';