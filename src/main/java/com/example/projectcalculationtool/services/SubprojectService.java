package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.repositories.interfaces.ISubprojectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubprojectService {
    private ISubprojectRepository subprojectRepository;

    public SubprojectService(ISubprojectRepository subprojectRepository) {
        this.subprojectRepository = subprojectRepository;
    }

    public void createSubproject(Subproject subproject){
        subprojectRepository.createSubproject(subproject);
    }

    public List<Subproject> getSubprojects(int projectID){
        return subprojectRepository.getSubprojects(projectID);
    }

    public Subproject getSubproject(int subprojectID){
        return subprojectRepository.getSubproject(subprojectID);
    }
    public  void updateSubproject(Subproject subproject){
        subprojectRepository.updateSubproject(subproject);
    }

}
