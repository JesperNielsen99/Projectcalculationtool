package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.repositories.interfaces.ISubprojectRepository;
import com.example.projectcalculationtool.services.comparators.SubprojectCompletedComparator;
import com.example.projectcalculationtool.services.comparators.SubprojectPriorityComparator;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        List<Subproject> subprojects = subprojectRepository.getSubprojects(projectID);
        //subprojects.sort(new SubprojectCompletedComparator().thenComparing(new SubprojectPriorityComparator()));
        return subprojects;
    }

    public Subproject getSubproject(int subprojectID){
        return subprojectRepository.getSubproject(subprojectID);
    }

    public void updateSubproject(Subproject subproject){
        subprojectRepository.updateSubproject(subproject);
    }

    public void deleteSubproject(int subprojectID){
        subprojectRepository.deleteSubproject(subprojectID);
    }

    public void updateSubprojectDuration(int subprojectID){
        subprojectRepository.updateSubprojectDuration(subprojectID);
    }
}
