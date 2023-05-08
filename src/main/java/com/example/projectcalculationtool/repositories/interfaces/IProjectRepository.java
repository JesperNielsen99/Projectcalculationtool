package com.example.projectcalculationtool.repositories.interfaces;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.models.User;

import java.util.List;

public interface IProjectRepository {

    void createProject(Project project);

    List<Project> getProjects(int managerID);

    void updateProject(Project project);
}
