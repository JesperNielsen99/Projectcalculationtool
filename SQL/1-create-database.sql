DROP DATABASE IF EXISTS projectcalculationtool_db;
CREATE DATABASE IF NOT EXISTS projectcalculationtool_db DEFAULT CHARACTER SET UTF8MB4;
USE projectcalculationtool_db;

CREATE TABLE role (
	role_id INTEGER NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE user (
    user_id INTEGER NOT NULL AUTO_INCREMENT,
    user_first_name VARCHAR(255) NOT NULL,
    user_last_name VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_role_id INT NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_role_id) REFERENCES role (role_id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE INDEX (user_email),
    CONSTRAINT check_user_email CHECK (user_email LIKE "%alpha.com")
);

CREATE TABLE project (
	project_id INTEGER NOT NULL AUTO_INCREMENT,
    project_manager_id INT NOT NULL,
    project_name VARCHAR(255) NOT NULL,
    project_duration INT,
    project_deadline DATE NOT NULL,
    project_completed TINYINT NOT NULL,
    PRIMARY KEY (project_id),
    FOREIGN KEY (project_manager_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE subproject (
	subproject_id INTEGER NOT NULL AUTO_INCREMENT,
    project_id INT NOT NULL,
    subproject_name VARCHAR(255) NOT NULL,
    subproject_priority INT NOT NULL,
    subproject_duration INT,
    subproject_deadline DATE NOT NULL,
    subproject_completed TINYINT NOT NULL,
    PRIMARY KEY (subproject_id),
    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE task (
	task_id INTEGER NOT NULL AUTO_INCREMENT,
    subproject_id INT NOT NULL,
    task_name VARCHAR(255) NOT NULL,
    task_description VARCHAR(255) NOT NULL,
    task_priority INT NOT NULL,
    task_duration INT NOT NULL,
    task_deadline DATE NOT NULL,
    task_completed TINYINT NOT NULL,
    PRIMARY KEY (task_id),
    FOREIGN KEY (subproject_id) REFERENCES subproject (subproject_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE task_user (
	task_id INT NOT NULL,
	user_id INT NOT NULL,
    FOREIGN KEY (task_id) REFERENCES task (task_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (task_id, user_id)
);