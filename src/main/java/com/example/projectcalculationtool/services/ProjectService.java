package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.repositories.interfaces.IProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectService {
    private IProjectRepository projectRepository;

    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void createProject(Project project){
        projectRepository.createProject(project);
    }

    public List<Project> getProjects(int managerID){
        List<Project> projects = projectRepository.getProjects(managerID);
        return projects;
    }

    public void updateProject(Project project) {
        projectRepository.updateProject(project);
    }

    public Project getProject(int projectID) {
        return projectRepository.getProject(projectID);
    }

    public void deleteProject(int projectID) {
        projectRepository.deleteProject(projectID);
    }

    public void updateProjectDuration(int projectID){
        projectRepository.updateProjectDuration(projectID);
    }
}
