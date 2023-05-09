package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Project;
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
public class ProjectRepositoryTest {



    @Autowired
    private ProjectRepository projectRepository;

    private ProjectTestDB projectTestDB;

    private Project project1;
    private Project project2;
    private Project project3;



    @BeforeEach
    void setUp(){
        projectTestDB = new ProjectTestDB();
        projectTestDB.projectTestDB();

        project1 = new Project(1,1,"T-Project1",1, LocalDate.now(),true);
        project2 = new Project(2,2,"T-Project2",2, LocalDate.now(),true);
        project3 = new Project(3,2,"T-Project3",3, LocalDate.now(),true);
    }

    /* ------------------------------------ Create, Size, Contains ----------------------------------------- */
    @Test
    void createProject(){
        projectRepository.createProject(project1);

        Project projectFound = projectRepository.getProject(project1.getProjectID());

        Assertions.assertEquals(project1.getName(), projectFound.getName());
    }

    @Test
    void assertSizeOneInstance(){
        projectRepository.createProject(project1);
        projectRepository.createProject(project2);

        List<Project> projectList = projectRepository.getProjects(1);

        Assertions.assertEquals(1, projectList.size());
    }

    @Test
    void assertSizeMultipleInstances(){
        projectRepository.createProject(project1);
        projectRepository.createProject(project2);
        projectRepository.createProject(project3);

        List<Project> projectList = projectRepository.getProjects(2);

        Assertions.assertEquals(2, projectList.size());
    }

    @Test
    void listContainsProject1(){
        projectRepository.createProject(project1);
        projectRepository.createProject(project2);

        List<Project> projectList = projectRepository.getProjects(1);

        Assertions.assertEquals(projectList.get(0).getName(), project1.getName());
    }

    /* ------------------------------------ Get projec(s) ----------------------------------------- */
    @Test
    void listContainsProject2(){
        projectRepository.createProject(project1);
        projectRepository.createProject(project2);

        List<Project> projectList = projectRepository.getProjects(1);

        Assertions.assertEquals(projectList.get(0).getName(), project1.getName());
    }

    @Test
    void getProject1(){
        projectRepository.createProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals(project1.getName(), projectFound.getName());
    }

    /* ------------------------------------ Update Project ----------------------------------------- */

    @Test
    void updateProject1IsCompleted(){
        projectRepository.createProject(project1);

        project1.setCompleted(false);
        projectRepository.updateProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals(project1.isCompleted(), projectFound.isCompleted());
    }

    @Test
    void updateProject1Name(){
        projectRepository.createProject(project1);

        project1.setName("New Description");
        projectRepository.updateProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals(project1.getName(), projectFound.getName());
    }

    @Test
    void updateProject1ManagerID() {
        projectRepository.createProject(project1);

        project1.setManagerID(4);
        projectRepository.updateProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals(project1.getManagerID(), projectFound.getManagerID());
    }

    @Test
    void updateProject1Duration() {
        projectRepository.createProject(project1);

        project1.setDuration(3);
        projectRepository.updateProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals(project1.getDuration(), projectFound.getDuration());
    }

    @Test
    void updateProject1Deadline() {
        projectRepository.createProject(project1);

        project1.setDeadline(LocalDate.now().plusDays(1));
        projectRepository.updateProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals(project1.getDeadline(), projectFound.getDeadline());
    }

    @Test
    void deleteProject(){
        projectRepository.createProject(project1);

        projectRepository.deleteProject(project1.getProjectID());

        Project projectFound = projectRepository.getProject(project1.getProjectID());

        Assertions.assertNull(projectFound);
    }


}
