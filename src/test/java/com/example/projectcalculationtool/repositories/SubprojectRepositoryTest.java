package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Subproject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class SubprojectRepositoryTest {

    @Autowired
    private SubprojectRepository subprojectRepository;
    private SubprojectTestDB subprojectTestDB;
    private Subproject subproject1;
    private Subproject subproject2;
    private Subproject subproject3;
    private int projectID;

    @BeforeEach
    void setUp(){
        subprojectTestDB = new SubprojectTestDB();
        subprojectTestDB.subprojectTestDB();

        projectID = 1;
        LocalDate localDate = LocalDate.now();

        subproject1 = new Subproject(1, projectID, "subproject1", 1, localDate, 1, false);
        subproject2 = new Subproject(2, projectID, "subproject2", 2, localDate, 2, false);
        subproject3 = new Subproject(3, projectID, "subproject3", 3, localDate, 3, false);

    }

    @Test
    void createSubproject() {
        subprojectRepository.createSubproject(subproject1);

        Subproject actualSubproject = subprojectRepository.getSubproject(subproject1.getSubprojectID());

        Assertions.assertEquals(subproject1.getName(), actualSubproject.getName());
    }

    @Test
    void getTasksSize(){
        subprojectRepository.createSubproject(subproject1);
        subprojectRepository.createSubproject(subproject2);
        subprojectRepository.createSubproject(subproject3);

        List<Subproject> actualSubprojects = subprojectRepository.getSubprojects(projectID);

        Assertions.assertEquals(3, actualSubprojects.size());
    }

    @Test
    void getTasksContainsSubproject2(){
        subprojectRepository.createSubproject(subproject1);
        subprojectRepository.createSubproject(subproject2);
        subprojectRepository.createSubproject(subproject3);

        List<Subproject> actualSubprojects = subprojectRepository.getSubprojects(projectID);

        Assertions.assertEquals(subproject2.getName(), actualSubprojects.get(1).getName());
    }


    @Test
    void getSubproject(){
        subprojectRepository.createSubproject(subproject1);

        Subproject actualSubproject = subprojectRepository.getSubproject(subproject1.getSubprojectID());

        Assertions.assertEquals(subproject1.getName(), actualSubproject.getName());
    }

    @Test
    void updateTaskName(){
        subprojectRepository.createSubproject(subproject1);
        subproject1.setName("Test1");

        subprojectRepository.updateSubproject(subproject1);
        Subproject actualSubproject = subprojectRepository.getSubproject(subproject1.getSubprojectID());

        Assertions.assertEquals(subproject1.getName(), actualSubproject.getName());

    }

    @Test
    void updateTaskPriority(){
        subprojectRepository.createSubproject(subproject1);
        subproject1.setPriority(10);

        subprojectRepository.updateSubproject(subproject1);
        Subproject actualSubproject = subprojectRepository.getSubproject(subproject1.getSubprojectID());

        Assertions.assertEquals(subproject1.getPriority(), actualSubproject.getPriority());
    }

    @Test
    void updateTaskDeadline(){
        subprojectRepository.createSubproject(subproject1);
        subproject1.setDeadline(LocalDate.of(2024, 01, 01));

        subprojectRepository.updateSubproject(subproject1);
        Subproject actualSubproject = subprojectRepository.getSubproject(subproject1.getSubprojectID());

        Assertions.assertEquals(subproject1.getDeadline(), actualSubproject.getDeadline());
    }

    @Test
    void updateTaskDuration(){
        subprojectRepository.createSubproject(subproject1);
        subproject1.setDuration(10);

        subprojectRepository.updateSubproject(subproject1);
        Subproject actualSubproject = subprojectRepository.getSubproject(subproject1.getSubprojectID());

        Assertions.assertEquals(subproject1.getDuration(), actualSubproject.getDuration());
    }

    @Test
    void updateTaskCompleted(){
        subprojectRepository.createSubproject(subproject1);
        subproject1.setCompleted(true);

        subprojectRepository.updateSubproject(subproject1);
        Subproject actualSubproject = subprojectRepository.getSubproject(subproject1.getSubprojectID());

        Assertions.assertEquals(subproject1.isCompleted(), actualSubproject.isCompleted());
    }

    @Test
    void deleteSubproject(){
        subprojectRepository.createSubproject(subproject1);

        subprojectRepository.deleteSubproject(subproject1.getSubprojectID());
        Subproject actualSubproject = subprojectRepository.getSubproject(subproject1.getSubprojectID());

        Assertions.assertNull(actualSubproject);
    }
}
