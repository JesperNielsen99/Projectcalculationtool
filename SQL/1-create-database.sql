DROP DATABASE IF EXISTS calculatedb;
CREATE DATABASE IF NOT EXISTS calculatedb DEFAULT CHARACTER SET UTF8MB4;
USE calculatedb;

DROP TABLE if exists users;

CREATE TABLE users (
    user_id INTEGER NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE INDEX (user_name)
);