package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.repositories.interfaces.IProjectRepo;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    IProjectRepo iProjectRepo;

    public ProjectService(IProjectRepo iProjectRepo) {
        this.iProjectRepo = iProjectRepo;
    }

    public void createProject(Project project){
        iProjectRepo.createProject(project);
    }

}
