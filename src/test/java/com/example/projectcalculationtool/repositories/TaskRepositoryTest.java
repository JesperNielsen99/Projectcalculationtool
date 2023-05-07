package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;
    private DB_Connector db_connector;
    private Task task1;
    private Task task2;


    @BeforeEach
    void setUp(){
        db_connector = new DB_Connector();
        db_connector.setUrl("jdbc:mysql://localhost:3306/projectcalculationtool_db");
        db_connector.setUser("root");
        db_connector.setPass("Jw-180490");

        task1 = new Task(3,1,"T-Task1", "T-Description1", 1,1, LocalDate.now(),false);
        task2 = new Task(4,1,"T-Task2", "T-Description2", 1,1, LocalDate.now(),false);
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

        Assertions.assertEquals(3,tasks.size());
    }

    @Test
    void getTasksAssertListContainsTask1() {
        taskRepository.createTask(task1);
        taskRepository.createTask(task2);

        List<Task> tasks = taskRepository.getTasks(1);

        Assertions.assertEquals(tasks.get(1).getName(),task1.getName());
    }

    @Test
    void getTasksAssertListContainsTask2() {
        taskRepository.createTask(task1);
        taskRepository.createTask(task2);

        List<Task> tasks = taskRepository.getTasks(1);

        Assertions.assertEquals(tasks.get(2).getName(),task2.getName());
    }

    @Test
    void getTask() {
        taskRepository.createTask(task1);

        Task taskFound = taskRepository.getTask(3);

        Assertions.assertEquals(task1.getName(),taskFound.getName());
    }

    @Test
    void updateTask() {
    }

    @Test
    void deleteTask() {
        taskRepository.createTask(task1);

        taskRepository.deleteTask(task1.getTaskID());

        Task taskFound = taskRepository.getTask(task1.getTaskID());
        Assertions.assertNull(taskFound);
    }

/*    @AfterEach
    void after(){
        taskRepository.deleteTask(task1.getTaskID());
        taskRepository.deleteTask(task2.getTaskID());
    }*/
}