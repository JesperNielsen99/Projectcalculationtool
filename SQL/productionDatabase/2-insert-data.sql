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

INSERT INTO task (subproject_id, task_name, task_description, task_priority, task_duration, task_deadline, task_completed) VALUES
	(1, 'methods', 'Do it better nutjob', 7, 2, current_date(), 0),
	(3, 'set-up',  'Fix it nutjob', 7, 3, current_date(), 0);

INSERT INTO task_user (task_id, user_id) VALUES
	(1,1),
    (2,1),
    (1,2),
    (1,3),
    (2,3),
    (2,4);