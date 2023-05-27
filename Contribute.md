# Contribute

## Prerequisites

- JDK 17: You should have JDK 17 installed on your machine as the project uses Java 17 as mentioned in the `pom.xml`.
- Apache Maven: As this is a Maven project, you need to have Maven installed on your machine.
- MySQL: As the project uses `mysql-connector-j` for database connectivity, you should have MySQL installed and properly set up on your machine.
- An IDE installed like VSCode, IntelliJ, or other.
- Knowledge of SQL database and how to use it and send/retrive data from it. 
- MySQL Workbench.
- Microsoft teams: This is our platform where we keep contact and you will be added to our group chat.

## Setup

1. Clone the repository.
2. Use git pull
3. thoroughly look through our code and how its been set up.
4. Setup your own local test database in your MySQL workbench.
5. Setup your own local production database in your MySQL workbench.
6. Run the program locally, we use port 8080, and use all the functions of the program to make sure it is fully functional.

## Workflow

In this project, we follow a feature branch workflow. Each contributor works on a feature in a separate branch. Here is the general process:

1. Create a new branch for your feature. The name of the branch should reflect the feature you're implementing.
2. Implement your feature and commit your changes to this branch.
3. After completing the feature, push the branch to the GitHub repository.
4. Our CI workflow will automatically attempt to rebase and build the branch with Maven. If there are no build failures (excluding tests), the changes will be automatically pushed to the `main` branch.
5. After your changes are in `main`, all team members should pull the latest changes from `main`.


# MySQL database, packet structure, Connector, Render, and Applications.properties

## MySQL database
- We keep two separate folders, with two sql files in each.
  - productionDatabase folder - here we have the production data for MySQL.
    - 1create-database.sql - this file contains the creating of all the tables.
    - 2insert-data-sql - this file contains the data that gets put into the tables.
  - testDatabase folder - here we have the test data for MySQL.
    - 1create-test-database.sql - this file contains the creating of all the tables.
    - 2insert-test-data-sql - this file contains the data that gets put into the tables.
- All order functions is being handled at the backend, before the data gets send back to the front end. 

## Packet structure
Our packets structure looks like this
- controllers 
- models
  - dto
- repositories
  - interfaces
  - util
- services

## How our structure flows
- Project, Subproject, Task, and User all have their own controller, model, repository, interface and service.
- Each project implements their own interface.
- We implement MVC and follow the principles of it.

## Introduction to our connector

We use a Connector class that looks like this
```
package com.example.projectcalculationtool.repositories.util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DB_Connector {
    private static String URL;
    private static String USER;
    private static String PASS;

    private static Connection connection;

    @Value("${spring.datasource.url}")
    public void setUrl(String url) {
        URL = url;
    }

    @Value("${spring.datasource.username}")
    public void setUser(String user) {
        USER = user;
    }

    @Value("${spring.datasource.password}")
    public void setPass(String pass) {
        PASS = pass;
    }

    public static Connection getConnection() {
        try {
            if (connection == null) connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
```

We use this Connector in all of our classes to establish a single connection, that stays open until the person terminates the program.

## Test database and production database
We have two separate applications.properties files.
- applications-prod.properties.
  - This is our production database that goes live. This database should **never** be altered until the implementations has been tested on our test database, passed all of its test, and every team member have approved the changes.
- applications-test.properties.
  - This is our test database that we run tests on. This database is **only** for experimental purposes, and can freely be tested on. If major changes to the database is required for a function to work, consult the rest of the team before making these changes, to avoid work going to waste.


## Render
We always have the newest build active on render because of our workflow. When a push happens from a feature branch, it will activate a workflow. If that workflow passes, the new feature branch will be pushed to main, and render will automatically build a container from an image based on our **docker file**, and make it accessible online. 
The YML file, docker file, and link to render can be found in the repository on github, or be directly access from here:

[Docker file](https://github.com/JesperNielsen99/Projectcalculationtool/blob/main/Dockerfile).

[YML file](https://github.com/JesperNielsen99/Projectcalculationtool/blob/main/.github/workflows/pushOntoMain.yml).

[Render](https://calculationtool.onrender.com/sign-in).



# Code style guide for java classes, html files, and SQL

## SQL
- We keep two separate folders, with two sql files in each.
  - productionDatabase folder - here we have the production data for MySQL.
    - 1create-database.sql - this file contains the creating of all the tables.
    - 2insert-data-sql - this file contains the data that gets put into the tables.
  - testDatabase folder - here we have the test data for MySQL.
    - 1create-test-database.sql - this file contains the creating of all the tables.
    - 2insert-test-data-sql - this file contains the data that gets put into the tables.
- Our writing conventions for MySQL and sql files*
  - When naming tables, we write the entire name in lower camel case, separating each full word with an underscore _
  - When naming columns in a table, we follow the same convention, as we do with naming tables.
- Calling conventions when retrieving data from the database.
  - Type all SQL command in uppercase for its entire word.
  - When making calls in a repository function, and the length exceeds half the length
    of the screen, we use linebreak and write the rest of the call, on the next line.
- All order functions is being handled at the backend, before the data gets send back to the front end.



## Java
- The name of the function will always reflect what the function does.
- When making a new function, always only make it in its respective repository and so on...
  - For example, when creating a function that retrieves all "projects", the function should be implemented in the "ProjectRepository".
- When making a function, always put it under its correct category. If a fitting headline does not exist, inform team so the team can find a new suitable name for the new category.
- All our SQL Strings will always be called **SQL**
- Always leave room when you reach a new "part" of a function.
  - Example can be seen here:
    ```
    @Override
    public List<Project> getProjects(int managerID) {
        List<Project> projectList = new ArrayList<>();

        try {
            Connection connection = DB_Connector.getConnection();

            String SQL = "SELECT * FROM project WHERE project_manager_id = ?\n" +
                    "ORDER BY project_completed";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1,managerID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                projectList.add(new Project(
                        resultSet.getInt("project_id"),
                        resultSet.getInt("project_manager_id"),
                        resultSet.getString("project_name"),
                        resultSet.getInt("project_duration"),
                        LocalDate.parse(resultSet.getString("project_deadline")),
                        resultSet.getBoolean("project_completed")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return projectList;
    }
    ```
- When creating any instances of any object, name the instance the same as what it represents, with the first letter being lowercase, and follow each new word withing it, with a uppercase.
  - **Example** --> Instance of PreparedStatement, should be named preparedStatement. Prepare is the first word, so it should be lowercase, while statement is the next word, and should therefore be uppercase. Example above, also shows this is practice.

## HTML files

We have four categories of HTML files.
- Create.
- Update.
- Show.
- Error.

All our categories follows the same coding style, since they do the same, but with different objects. We use thymeleaf to add them as models, so we can use thymeleaf to access the objects data, in our HTML file.

Example of one of our HTML create files.

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Create Project</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
  <link rel="stylesheet" href="/style.css">
</head>
<body>
  <div class="create-form">
    <div class="create-box shadow p-4 mb-5 rounded">
      <h1 class="header-one">Create project</h1>
      <br>
      <form th:action="@{/project/create}" th:method="post" th:object="${project}">
        <div>
          <input type=hidden id="managerID" th:field="*{managerID}">
        </div>

        <div class="input-group mb-3">
          <span class="input-group-text">Name</span>
          <input type="text" id="projectName" name="projectName" class="form-control" th:field="*{name}" placeholder="Input project name" required autofocus>
        </div>

        <div class="input-group mb-3">
          <span class="input-group-text">Deadline</span>
          <input type="date" th:min="${#temporals.format(#temporals.createNow(),'yyyy-MM-dd')}" th:max="9999-12-31"  id="deadline" name="deadline" class="form-control" th:field="*{deadline}">
        </div>

        <input type="hidden" id="duration" name="duration" th:field="*{duration}">

        <div class="btn-group d-flex justify-content-between" role="group" aria-label="Basic example">
          <button class= "btn btn-primary" type="button" onclick="history.back()">Back</button>
          <button class= "btn btn-primary" type="reset">Reset</button>
          <button class= "btn btn-primary" type="submit">Submit</button>
        </div>
      </form>
    </div>
  </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>
```
