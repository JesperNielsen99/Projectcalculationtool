package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.repositories.interfaces.IProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    IProjectRepository iProjectRepository;

    public ProjectService(IProjectRepository iProjectRepository) {
        this.iProjectRepository = iProjectRepository;
    }

    public void createProject(Project project){
        iProjectRepository.createProject(project);
    }

}
