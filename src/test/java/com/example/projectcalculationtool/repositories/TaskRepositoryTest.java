package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.models.User;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskTestDB testDB;

    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        testDB.taskTestDB();

        task1 = new Task(1, 1, "T-Task1", "T-Description1", 1, 1, LocalDate.now(), false, "T-manager1");
        task2 = new Task(2, 1, "T-Task2", "T-Description2", 1, 1, LocalDate.now(), false, "T-manager2");
    }

    @Test
    void createTask() {
        taskRepository.createTask(task1);

        Task taskFound = taskRepository.getTask(task1.getTaskID());

        Assertions.assertEquals(task1.getName(),taskFound.getName());
    }


    @Test
    void createTaskWithMaxCharacterName(){
        String randomCharacters = RandomString.make(50);
        task1.setDescription(randomCharacters);

        taskRepository.createTask(task1);
        Task taskFound = taskRepository.getTask(task1.getTaskID());

        Assertions.assertEquals(randomCharacters, taskFound.getDescription());
    }

    @Test
    void createTaskWithNullNameException(){
        String invalidName = null;

        task1.setName(invalidName);

        Assertions.assertThrows(RuntimeException.class, () -> {
            taskRepository.createTask(task1);
        });
    }

    @Test
    void createTaskWithOverMaxCharactersNameException(){
        String randomCharacters = RandomString.make(51);
        task1.setName(randomCharacters);

        Assertions.assertThrows(RuntimeException.class, () -> {
            taskRepository.createTask(task1);
        });
    }

    @Test
    void createTaskWithMaxCharacterOf255(){
        String randomCharacters = RandomString.make(255);
        task1.setDescription(randomCharacters);

        taskRepository.createTask(task1);
        Task taskFound = taskRepository.getTask(task1.getTaskID());

        Assertions.assertEquals(randomCharacters, taskFound.getDescription());

    }

    @Test
    void createTaskWithNullValueInDescriptionException(){
        String invalidNullValue = null;

        task1.setDescription(invalidNullValue);

        Assertions.assertThrows(RuntimeException.class, () -> {
            taskRepository.createTask(task1);
        });
    }

    @Test
    void createTaskWith256CharactersInDescriptionException(){
        String randomCharacters = RandomString.make(256);

        task1.setDescription(randomCharacters);

        Assertions.assertThrows(RuntimeException.class, () -> {
            taskRepository.createTask(task1);
        });
    }

    @Test
    void createTaskWithMinimumPriority(){
        int minValidNumber = 1;

        task1.setPriority(minValidNumber);
        taskRepository.createTask(task1);

        Task taskFound = taskRepository.getTask(task1.getTaskID());

        Assertions.assertEquals(task1.getPriority(), taskFound.getPriority());
    }

    @Test
    void createTaskWithMaxPriority(){
        int maxValidNumber = 5;

        task1.setPriority(maxValidNumber);
        taskRepository.createTask(task1);

        Task taskFound = taskRepository.getTask(task1.getTaskID());

        Assertions.assertEquals(task1.getPriority(), taskFound.getPriority());
    }

    @Test
    void createTaskWithUnderMinimumPriorityNumberException(){
        int invalidNumber = 0;

        task1.setPriority(invalidNumber);

        Assertions.assertThrows(RuntimeException.class, () -> {
            taskRepository.createTask(task1);
        });
    }

    @Test
    void createTaskWithAnOverMaxPriorityNumberException(){
        int invalidNumber = 6;

        task1.setPriority(invalidNumber);

        Assertions.assertThrows(RuntimeException.class, () -> {
            taskRepository.createTask(task1);
        });
    }

    @Test
    void createTaskWithMaxDate(){
        LocalDate maxDate = LocalDate.parse("3000-12-31");
        task1.setDeadline(maxDate);
        taskRepository.createTask(task1);

        Task taskFound = taskRepository.getTask(task1.getTaskID());

        Assertions.assertEquals(maxDate,taskFound.getDeadline());

    }

    @Test
    void createTaskWithOverMaxDate(){
        LocalDate maxDate = LocalDate.parse("3001-01-01");
        task1.setDeadline(maxDate);

        Assertions.assertThrows(RuntimeException.class, () -> {
            taskRepository.createTask(task1);
        });
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

        Task taskFound = taskRepository.getTask(task1.getTaskID());
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

        task1.setCompleted(true);
        taskRepository.updateTask(task1);

        Task taskFound = taskRepository.getTask(1);
        Assertions.assertEquals(task1.isCompleted(),taskFound.isCompleted());
    }

    @Test
    void updateTaskPlus255CharactersException(){
        taskRepository.createTask(task1);

        String randomChar = RandomString.make(260);
        task1.setDescription(randomChar);

        Assertions.assertThrows(RuntimeException.class, () -> {
            taskRepository.updateTask(task1);
        });
    }


    @Test
    void deleteTask() {
        taskRepository.createTask(task1);

        taskRepository.deleteTask(task1.getTaskID());

        Task taskNotFound = taskRepository.getTask(task1.getTaskID());
        Assertions.assertNull(taskNotFound);
    }

    // New tests

    @Test
    void addAssignedUsersToTask(){
        //creating task
        taskRepository.createTask(task1);
        //adding people to task
        List<User> addedUsersList = new ArrayList<>(List.of());
        //taskRepository.addAssignedUsersToTask();
    }

    @Test
    void getUserTasks(){

    }

    @Test
    void getTaskUsersDTO(){

    }

    @Test
    void removeAssignedUsersFromTask(){

    }
}