USE projectcalculationtool_db;

INSERT INTO role (role_name) VALUES
	('Admin'),
    ('User');

INSERT INTO user (user_first_name, user_last_name, user_email, user_password, user_role_id) VALUES
    ('Thomas', 'Løvkilde', 'thomlov@alpha.com', '123', '1'),
	('Jesper', 'Nielsen', 'sycko@alpha.com', '123', '1'),
    ('Jesper', 'Zamora', 'jesper@alpha.com', '123', '2'),
    ('Andreas', 'Hjordt', 'sycko1@alpha.com', '123', '2'),
    ('John', 'Doe', 'john@alpha.com', '123', '2'),
    ('Jane', 'Doe', 'jane@alpha.com', '123', '2');

INSERT INTO project (project_manager_id, project_name, project_deadline, project_completed) VALUES
	(1, 'test1', current_date(), 0),
    (2, 'test2', current_date(), 1);

INSERT INTO subproject (project_id, subproject_name, subproject_priority, subproject_deadline, subproject_completed) VALUES
	(1, 'create', 2, '2023-06-10', 0),
	(1, 'test', 5, '2023-10-30', 0),
	(2, 'init', 1, current_date(), 2);

INSERT INTO task (subproject_id, task_name, task_description, task_priority, task_duration, task_deadline, task_completed, task_manager_name) VALUES
	(1, 'methods', 'Create methods for this task', 3, 2, current_date(), 0, "Thomas Løvkilde"),
	(3, 'set-up',  'Setup classes for this task', 4, 3, current_date(), 0, "Jesper Nielsen");

INSERT INTO task_user (task_id, user_id) VALUES
    (1,3),
    (1,4),
    (2,4),
    (2,5),
    (2,6);