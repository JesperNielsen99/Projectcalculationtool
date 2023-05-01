package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.repositories.CalculateRepository;
import com.example.projectcalculationtool.repositories.interfaces.IUserRepo;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    IUserRepo iUserRepo;

    public ProjectService(IUserRepo iUserRepo) {
        this.iUserRepo = iUserRepo;
    }


}
