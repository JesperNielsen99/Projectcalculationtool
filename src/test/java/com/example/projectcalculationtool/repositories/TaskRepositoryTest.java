package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
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
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DB_Connector db_connector;
    private TaskTestDB testDB;

    private Task task1;
    private Task task2;


    @BeforeEach
    void setUp() {

        db_connector.setUrl("jdbc:mysql://localhost:3306/tasktest_db");
        db_connector.setUser("root");
        db_connector.setPass("Jw-180490");
        testDB = new TaskTestDB();
        testDB.taskTestDB();

        task1 = new Task(1,1,"T-Task1", "T-Description1", 1,1, LocalDate.now(),false);
        task2 = new Task(2,1,"T-Task2", "T-Description2", 1,1, LocalDate.now(),false);
    }

    @Test
    void createTask() {
        taskRepository.createTask(task1);

        Task taskFound = taskRepository.getTask(task1.getTaskID());

        Assertions.assertEquals(task1.getName(),taskFound.getName());
    }

    @Test
    void getTasksAssertForEqualSize() {
        taskRepository.createTask(task1);
        taskRepository.createTask(task2);

        List<Task> tasks = taskRepository.getTasks(1);

        Assertions.assertEquals(2,tasks.size());
    }

    @Test
    void getTasksAssertListContainsTask1() {
        taskRepository.createTask(task1);
        taskRepository.createTask(task2);

        List<Task> tasks = taskRepository.getTasks(1);

        Assertions.assertEquals(tasks.get(0).getName(),task1.getName());
    }

    @Test
    void getTasksAssertListContainsTask2() {
        taskRepository.createTask(task1);
        taskRepository.createTask(task2);

        List<Task> tasks = taskRepository.getTasks(1);

        Assertions.assertEquals(tasks.get(1).getName(),task2.getName());
    }

    @Test
    void getTask() {
        taskRepository.createTask(task1);

        Task taskFound = taskRepository.getTask(1);

        Assertions.assertEquals(task1.getName(),taskFound.getName());
    }

    @Test
    void updateTaskName() {
        taskRepository.createTask(task1);

        task1.setName("T-Task3");
        taskRepository.updateTask(task1);

        Task taskFound = taskRepository.getTask(1);
        Assertions.assertEquals(task1.getName(),taskFound.getName());
    }

    @Test
    void updateTaskDescription() {
        taskRepository.createTask(task1);

        task1.setDescription("T-Description3");
        taskRepository.updateTask(task1);

        Task taskFound = taskRepository.getTask(1);
        Assertions.assertEquals(task1.getDescription(),taskFound.getDescription());
    }

    @Test
    void updateTaskIsCompletedTrue() {
        taskRepository.createTask(task1);

        task1.setIsCompleted(true);
        taskRepository.updateTask(task1);

        Task taskFound = taskRepository.getTask(1);
        Assertions.assertEquals(task1.getIsCompleted(),taskFound.getIsCompleted());
    }

    @Test
    void deleteTask() {
        taskRepository.createTask(task1);

        taskRepository.deleteTask(task1.getTaskID());

        Task taskFound = taskRepository.getTask(task1.getTaskID());
        Assertions.assertNull(taskFound);
    }

}