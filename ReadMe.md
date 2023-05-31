# Projectcalculations tool

## A fully minimalistic planning tool for projects of all sizes

This project has been carried out by four programming students as part of their second semester examinations. The project was realized in collaboration with our educational institution (Københavns Erhvers Akademi) and Alpha Solutions. 
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

Since we are using the free version of Render, keep in mind that it is very slow and the initial startup will take a 
couple of minutes and afterwards it will still be quite slow.

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
