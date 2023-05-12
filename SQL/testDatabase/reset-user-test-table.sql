SET foreign_key_checks = 0;
DROP TABLE IF EXISTS user;

CREATE TABLE user (
	user_id         INTEGER      NOT NULL AUTO_INCREMENT,
	user_first_name VARCHAR(255) NOT NULL,
	user_last_name  VARCHAR(255) NOT NULL,
	user_email      VARCHAR(255) NOT NULL,
	user_password   VARCHAR(255) NOT NULL,
	user_role_id    INT          NOT NULL,
	PRIMARY KEY (user_id),
	FOREIGN KEY (user_role_id) REFERENCES role (role_id) ON DELETE CASCADE ON UPDATE CASCADE,
	UNIQUE INDEX (user_email),
	CONSTRAINT check_user_email CHECK (user_email LIKE "%alpha.com")
);

INSERT INTO user (user_first_name, user_last_name, user_email, user_password, user_role_id) VALUES
    ('Thomas', 'Løvkilde', 'thomløv@alpha.com', '123', '1'),
    ('Jesper', 'Nielsen', 'sycko@alpha.com', '123', '1'),
    ('Jesper', 'Zamora', 'jesper@alpha.com', '123', '2'),
    ('Andreas', 'Hjordt', 'sycko1@alpha.com', '123', '2'),
    ('Test', 'test', 'test@alpha.com', '1', '2');