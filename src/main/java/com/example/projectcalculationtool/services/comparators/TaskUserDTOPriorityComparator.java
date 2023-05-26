package com.example.projectcalculationtool.services.comparators;

import com.example.projectcalculationtool.models.dto.TaskUserDTO;

import java.util.Comparator;

public class TaskUserDTOPriorityComparator implements Comparator<TaskUserDTO> {
    @Override
    public int compare(TaskUserDTO o1, TaskUserDTO o2) {
        return Integer.compare(o1.getTask().getPriority(),o2.getTask().getPriority());
    }
}
