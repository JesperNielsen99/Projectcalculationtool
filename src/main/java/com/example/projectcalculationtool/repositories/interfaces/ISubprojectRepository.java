package com.example.projectcalculationtool.repositories.interfaces;

import com.example.projectcalculationtool.models.Subproject;

import java.util.List;

public interface ISubprojectRepository {

    void createSubproject(Subproject subproject);

    List<Subproject> getSubprojects(int projectID);

    Subproject getSubproject(int subprojectID);

    void updateSubproject(Subproject subproject);

    void deleteSubproject(int subprojectID);

    void updateSubprojectDuration(int subprojectID);
}
