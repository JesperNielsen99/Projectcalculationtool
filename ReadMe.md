# Projectcalculations tool

## a fully minimalistic planning tool for projects of all sizes

This project has been carried out by four programming students as part of their second semester examinations. The project was realized in collaboration with our educational institution and Alpha Solutions. 
We've developed a planning tool that allows companies to structure their projects and break them down into smaller components. 
The purpose of this project is to create a planning tool that is simple, quick, and intuitive to use, and isn't burdened with unnecessary features.

Some of the highlights of our program is:
- Role system to make it easier to administer.
- Dynamic implementation of javascript to make interactive sites.
- Intuitive design and "helping text" to clarify what inputs is legal.
- Build-in safety features in front-end to make sure users cant input illegal inputs.
- Fail-safe options, that will take user to login if and unexpected error happens.

## Link to the site
[Render](https://calculationtool.onrender.com/sign-in)

### First time you access the site
If you are accessing the site for the first you, you need to create a profile.
You can either go to "sign-up" when going to the link, or you can go directly to sign-up through this link: [Sign-up link](https://calculationtool.onrender.com/sign-up)

## Program features

Our program represents a CRUD system, which stands for create, read, update, and delete. The program consists of 4 major parts.

- Users, that can be one of two roles.
  - Users, this role is the normal role, with limited functionality and access to sites.
  - Admin, this is the administrator role with full access to functions and sites.
- Projects.
- Subprojects, that are connected to its own single project.
- Tasks, that are connected to its own single subproject.

Projects, Subprojects, tasks, and users have all the CRUD commands. 

## Roles and their features

### Users

Users are the standard role, and does only have access to the tasks page. On the task page they will only see the tasks they have been assigned too. 
Besides that, they can do the following.
- See list of all assigned members to the desired task.
- Update any of the task's information.
- Delete any task.
- See the description of any task.

### Admin

Admin, or administrator role have access to all functions and sites of the program. 
In addition to the previous mentioned functions, they can also do/access the following.
- Create any number of projects, subprojects and tasks.
- Update any number of projects, subprojects and tasks.
- Delete any number of projects, subprojects and tasks.
- See all information of any projects, subprojects and tasks.
- Assign members to any amount of tasks.
- Unassigned members to any amount of tasks.

## Access points and what they can do.

We have the following access points.
But keep in mind, some of the functionality will be limited depending on what role is logged in at the time.

We will use the access points on render.

[https://calculationtool.onrender.com/sign-in](https://calculationtool.onrender.com/sign-in) --> This is where a user can log in.

[https://calculationtool.onrender.com/sign-up](https://calculationtool.onrender.com/sign-up) --> This is where a new user can create an account.

[https://calculationtool.onrender.com/projects](https://calculationtool.onrender.com/projects) --> This is where all the created projects can be seen.

[https://calculationtool.onrender.com/project/create](https://calculationtool.onrender.com/project/create) --> This is where a project can be created.

[https://calculationtool.onrender.com/project/create](https://calculationtool.onrender.com/project/subprojects) --> This is where all created subprojects can be seen.

[https://calculationtool.onrender.com/project/create](https://calculationtool.onrender.com/project/subproject/create) --> This is where a subproject can be created.

[https://calculationtool.onrender.com/project/create](https://calculationtool.onrender.com/project/subproject/tasks) --> This is where all created tasks can be seen.

[https://calculationtool.onrender.com/project/create](https://calculationtool.onrender.com/project/subproject/task/create) --> This is where a task can be created.

Since we use HttpSession the links might not work if a user isn't logged in, or the user does not have the correct role, to access that part/functionality of the site.


## For users who want to run the program locally

You can find the two SQL code snippets through these links, or paste code snippets directly from below:

[Database creating](https://github.com/JesperNielsen99/Projectcalculationtool/blob/main/SQL/productionDatabase/1-create-database.sql).

[Database insert](https://github.com/JesperNielsen99/Projectcalculationtool/blob/main/SQL/productionDatabase/2-insert-data.sql)

Make sure to run the two SQL file in the order as shown above.

```
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
    task_manager_name VARCHAR(255) NOT NULL,
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
```


```
USE projectcalculationtool_db;

INSERT INTO role (role_name) VALUES
	('Admin'),
    ('User');

INSERT INTO user (user_first_name, user_last_name, user_email, user_password, user_role_id) VALUES
    ('Thomas', 'Løvkilde', 'thomløv@alpha.com', '123', '1'),
	('Jesper', 'Nielsen', 'sycko@alpha.com', '123', '1'),
    ('Jesper', 'Zamora', 'jesper@alpha.com', '123', '2'),
    ('Andreas', 'Hjordt', 'sycko1@alpha.com', '123', '2');

INSERT INTO project (project_manager_id, project_name, project_duration, project_deadline, project_completed) VALUES
	(1, 'test1', 0, current_date(), 0),
    (2, 'sheite', 0, current_date(), 1);

INSERT INTO subproject (project_id, subproject_name, subproject_priority, subproject_duration, subproject_deadline, subproject_completed) VALUES
	(1, 'create', 2, 0, '2023-06-10', 0),
	(1, 'test', 1, 0, '2023-10-30', 0),
	(2, 'init', 10000, 0, current_date(), 2);

INSERT INTO task (subproject_id, task_name, task_description, task_priority, task_duration, task_deadline, task_completed, task_manager_name) VALUES
	(1, 'methods', 'Do it better nutjob', 7, 2, current_date(), 0, "Thomas Løvkilde"),
	(3, 'set-up',  'Fix it nutjob', 7, 3, current_date(), 0, "Jesper Nielsen");

INSERT INTO task_user (task_id, user_id) VALUES
	(1,1),
    (2,1),
    (1,2),
    (1,3),
    (2,3),
    (2,4);
```