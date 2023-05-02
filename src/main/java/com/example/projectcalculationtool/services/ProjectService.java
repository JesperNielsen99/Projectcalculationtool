package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.repositories.interfaces.IProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    IProjectRepository iProjectRepo;

    public ProjectService(IProjectRepository iProjectRepo) {
        this.iProjectRepo = iProjectRepo;
    }

    public void createProject(Project project){
        iProjectRepo.createProject(project);
    }

}
