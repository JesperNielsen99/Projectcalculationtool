# Contributing to Projectcalculationtool

First off, thanks for taking the time to contribute!

The following is a set of guidelines for contributing to Projectcalculationtool, which is hosted on GitHub. These are just guidelines, not rules. Use your best judgment, and feel free to propose changes to this document in a contribution.

## Prerequisites

- JDK 17: You should have JDK 17 installed on your machine as the project uses Java 17 as mentioned in the `pom.xml`.
- Apache Maven: As this is a Maven project, you need to have Maven installed on your machine.
- MySQL: As the project uses `mysql-connector-j` for database connectivity, you should have MySQL installed and properly set up on your machine.

## Setup

1. Clone the repository.
2. Navigate to the project directory and run `mvn install` to download the dependencies.
3. To start the application, use `mvn spring-boot:run`.

## Workflow

In this project, we follow a feature branch workflow. Each contributor works on a feature in a separate branch. Here is the general process:

1. Create a new branch for your feature. The name of the branch should reflect the feature you're implementing.
2. Implement your feature and commit your changes to this branch.
3. After completing the feature, push the branch to the GitHub repository.
4. Our CI workflow will automatically attempt to rebase and build the branch with Maven. If there are no build failures (excluding tests), the changes will be automatically pushed to the `main` branch.
5. After your changes are in `main`, all team members should pull the latest changes from `main`.

## How Can I Contribute?

### Reporting Bugs

This section guides you through submitting a bug report for Projectcalculationtool. Following these guidelines helps maintainers and the community understand your report, reproduce the behavior, and find related reports.

### Suggesting Enhancements

This section guides you through submitting an enhancement suggestion for Projectcalculationtool, including completely new features and minor improvements to existing functionality. Following these guidelines helps maintainers and the community understand your suggestion and make decisions.

### Style Guide

Please follow the standard Java coding conventions. This ensures that the source code is easy to read and reduces the chance of introducing bugs.

## Community

You can chat with the core contributors and the Projectcalculationtool community on Microsoft Teams. We have a group chat there where we discuss about the project, share ideas, and solve problems.

Thank you for contributing to the Projectcalculationtool!
