package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.repositories.interfaces.ISubprojectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubprojectService {
    private ISubprojectRepository subprojectRepository;

    public SubprojectService(ISubprojectRepository subprojectRepository) {
        this.subprojectRepository = subprojectRepository;
    }

    public void createSubproject(Subproject subproject){
        subprojectRepository.createSubproject(subproject);
    }

}
