package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.models.User;
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
    private Project project4;

    @BeforeEach
    void setUp(){
        projectTestDB = new ProjectTestDB();
        projectTestDB.projectTestDB();

        project1 = new Project(1,1,"T-Project1",1, LocalDate.now(),true);
        project2 = new Project(2,2,"T-Project2",2, LocalDate.now(),true);
        project3 = new Project(3,2,"T-Project3",3, LocalDate.now(),true);
        project4 = new Project(4,2,"T-Project4",4, LocalDate.now(),true);
    }

    /* ------------------------------------ Create, Size, Contains ----------------------------------------- */

    @Test
    void createProject(){
        projectRepository.createProject(project1);

        Project projectFound = projectRepository.getProject(project1.getProjectID());

        Assertions.assertEquals(project1.getName(), projectFound.getName());
    }
    @Test
    void createProject2(){
        projectRepository.createProject(project1);
        projectRepository.createProject(project2);

        Project projectFound = projectRepository.getProject(project2.getProjectID());

        Assertions.assertEquals(project2.getName(), projectFound.getName());
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

    /* ------------------------------------ Delete Project ----------------------------------------- */
    @Test
    void deleteProject(){
        projectRepository.createProject(project1);

        projectRepository.deleteProject(project1.getProjectID());

        Project projectFound = projectRepository.getProject(project1.getProjectID());

        Assertions.assertNull(projectFound);
    }
    @Test
    void getProjectCorrectly() {
        int managerID = 1;

        projectRepository.createProject(project1);

        Project project = projectRepository.getProject(managerID);

        Assertions.assertNotNull(project);
    }
    @Test
    void getProjectsWithDifferentManagerID() {
        projectRepository.createProject(project1);
        projectRepository.createProject(project2);

        List<Project> projectList = projectRepository.getProjects(3);

        Assertions.assertTrue(projectList.isEmpty());
    }
    @Test
    void getProjectsWhenNoProjectsExist() {
        List<Project> projectList = projectRepository.getProjects(1);

        Assertions.assertTrue(projectList.isEmpty());
    }
    @Test
    void getProjectsForMultipleManagers() {
        projectRepository.createProject(project1);
        projectRepository.createProject(project2);
        projectRepository.createProject(project3);

        List<Project> projectsManager1 = projectRepository.getProjects(1);
        List<Project> projectsManager2 = projectRepository.getProjects(2);

        boolean condition = projectsManager1.size() == 1 && projectsManager2.size() == 2;
        Assertions.assertTrue(condition);
    }
    @Test
    void updateProjectWithMultipleProjects() {
        projectRepository.createProject(project1);
        projectRepository.createProject(project2);

        project1.setDuration(3);
        projectRepository.updateProject(project1);

        List<Project> projects = projectRepository.getProjects(project1.getManagerID());

        boolean updated = false;
        for (Project project : projects) {
            if (project.getProjectID() == 1 && project.getDuration() == 3) {
                updated = true;
                break;
            }
        }
        Assertions.assertTrue(updated);
    }
    @Test
    void deleteProjectWithMultipleProjects() {
        projectRepository.createProject(project1);
        projectRepository.createProject(project2);

        projectRepository.deleteProject(1);

        List<Project> projects = projectRepository.getProjects(project1.getManagerID());

        boolean deleted = true;
        for (Project project : projects) {
            if (project.getProjectID() == 1) {
                deleted = false;
                break;
            }
        }
        Assertions.assertTrue(deleted);
    }
    @Test
    void updateProjectWithNegativeDuration() {
        projectRepository.createProject(project1);

        project1.setDuration(-5);
        projectRepository.updateProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals(-5, projectFound.getDuration());
    }
    @Test
    void deleteNonExistentProject() {
        projectRepository.createProject(project1);

        // Attempt to delete a project with an ID that does not exist in the repository.
        projectRepository.deleteProject(3);

        Project project1Found = projectRepository.getProject(1);

        // Ensure that project1 still exists after trying to delete a non-existent project.
        Assertions.assertNotNull(project1Found);
    }
    @Test
    void getProjectsWithNonExistentManagerID() {
        projectRepository.createProject(project1);
        projectRepository.createProject(project2);

        List<Project> projectList = projectRepository.getProjects(999);

        Assertions.assertTrue(projectList.isEmpty());
    }
    @Test
    void updateProjectWithPastDeadline() {
        projectRepository.createProject(project1);

        project1.setDeadline(LocalDate.now().minusDays(10));
        projectRepository.updateProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals(LocalDate.now().minusDays(10), projectFound.getDeadline());
    }
    @Test
    void createMultipleProjectsWithSameManagerID() {
        projectRepository.createProject(project1);
        projectRepository.createProject(new Project(4, 1, "T-Project4", 1, LocalDate.now().plusDays(5), false));

        List<Project> projectList = projectRepository.getProjects(1);

        Assertions.assertEquals(2, projectList.size());
    }
    @Test
    void updateProjectManagerIDToNonExistingManagerID() {
        projectRepository.createProject(project1);

        project1.setManagerID(999);
        projectRepository.updateProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals(999, projectFound.getManagerID());
    }
    @Test
    void updateProjectNameToEmptyString() {
        projectRepository.createProject(project1);

        project1.setName("");
        projectRepository.updateProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals("", projectFound.getName());
    }
    @Test
    void updateProjectDurationToZero() {
        projectRepository.createProject(project1);

        project1.setDuration(0);
        projectRepository.updateProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals(0, projectFound.getDuration());
    }
    @Test
    void updateProjectDeadlineToSameDate() {
        projectRepository.createProject(project1);

        LocalDate sameDeadline = project1.getDeadline();
        project1.setDeadline(sameDeadline);
        projectRepository.updateProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals(sameDeadline, projectFound.getDeadline());
    }
    @Test
    void updateProjectCompletionStatusToSameStatus() {
        projectRepository.createProject(project1);

        project1.setCompleted(true);
        projectRepository.updateProject(project1);

        Project projectFound = projectRepository.getProject(1);

        Assertions.assertEquals(true, projectFound.isCompleted());
    }
    @Test
    void getEmptyListOfProjectsWithNonExistingManagerID() {
        projectRepository.createProject(project1);

        List<Project> projectList = projectRepository.getProjects(999);

        Assertions.assertTrue(projectList.isEmpty());
    }
}
