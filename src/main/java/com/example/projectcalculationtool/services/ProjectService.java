package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.repositories.interfaces.IProjectRepository;
import org.springframework.stereotype.Service;

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

    public List<Project> getProjects(int ID){ //session managerID
        return projectRepository.getProjects(ID);
    }

    public void updateProject(Project project) {
        projectRepository.updateProject(project);
    }
}
