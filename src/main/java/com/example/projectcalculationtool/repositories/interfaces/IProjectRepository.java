package com.example.projectcalculationtool.repositories.interfaces;

import com.example.projectcalculationtool.models.Project;

import java.util.List;

public interface IProjectRepository {

    void createProject(Project project);

    List<Project> getProject();

}
