package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Subproject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
public class SubprojectRepositoryTest {

    @Autowired
    private SubprojectRepository subprojectRepository;
    private SubprojectTestDB subprojectTestDB;
    private Subproject subproject1;
    private Subproject subproject2;
    private Subproject subproject3;

    @BeforeEach
    void setUp(){
        subprojectTestDB = new SubprojectTestDB();
        subprojectTestDB.subprojectTestDB();

        subproject1 = new Subproject(1, 1, "1", 1, LocalDate.now(), 1, false);
        subproject2 = new Subproject(2, 2, "2", 2, LocalDate.now(), 2, false);
        subproject3 = new Subproject(3, 3, "3", 3, LocalDate.now(), 3, false);

    }

    @Test
    void createSubproject() {
        subprojectRepository.createSubproject(subproject1);

        Subproject foundSubproject = subprojectRepository.getSubproject(subproject1.getSubprojectID());

        Assertions.assertEquals(subproject1.getName(), foundSubproject.getName());
    }


}
