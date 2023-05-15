# Contributing to Projectcalculationtool

First off, thanks for taking the time to contribute!

The following is a set of guidelines for contributing to Projectcalculationtool, which is hosted on GitHub. These are just guidelines, not rules. Use your best judgment, and feel free to propose changes to this document in a contribution.

## Prerequisites

- JDK 17: You should have JDK 17 installed on your machine as the project uses Java 17 as mentioned in the `pom.xml`.
- Apache Maven: As this is a Maven project, you need to have Maven installed on your machine.
- MySQL: As the project uses `mysql-connector-j` for database connectivity, you should have MySQL installed and properly set up on your machine.
- An IDE installed like VSCode, IntelliJ, or other.
- Knowledge of SQL database and how to use it and send/retrive data from it. 
- MySQL Workbench.

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

## How Can I Contribute?

### Help us test the program

Help us test the program by heading to [**https://calculationtool.onrender.com/sign-up**](https://calculationtool.onrender.com/sign-up). Create a account and try to use the program and all of its functions.

### Reporting Bugs

If you find a bug consider creating a issue with it. Here we will look further into the issue and take in generel feedback about the programming and how it can be improved.

When reporting on a bug, we strongly advice you to use the following ***How to report on a bug***

***How to report on a bug, step by step***
- Create a issue with the title BUG.
- Include step by step details as to what you did, prior to the bug happening.
- Insert a picture of the screen where you clearly can see the redirected error page and the URL.

### Suggesting Enhancements

If you have suggestions to implementations of new features or generel ideas to enhance the program, please create a issue

We consider there to be to type of issues in this area that are
- ***Ideas to enhance the program***
  - This could be optimization of functions, better arrangement of backend code, better arrangement of frontend code, and so on. 
- ***Ideas to new features***
  - These are new functions that will evolve the programming that users can benafit from.

### Style Guide

Please follow the standard Java coding conventions. This ensures that the source code is easy to read and reduces the chance of introducing bugs.

## Community

You can chat with the core contributors and the Projectcalculationtool community on Microsoft Teams. We have a group chat there where we discuss about the project, share ideas, and solve problems.

Thank you for contributing to the Projectcalculationtool!
