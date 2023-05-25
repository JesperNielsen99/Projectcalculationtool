package com.example.projectcalculationtool.services.comparators;

import com.example.projectcalculationtool.models.dto.TaskUserDTO;

import java.util.Comparator;

public class TaskUserDTOCompletedComparator implements Comparator<TaskUserDTO> {
    @Override
    public int compare(TaskUserDTO o1, TaskUserDTO o2) {
        return Boolean.compare(o1.getTask().isCompleted(), o2.getTask().isCompleted());
    }
}
