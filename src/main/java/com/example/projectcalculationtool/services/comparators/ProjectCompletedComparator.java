package com.example.projectcalculationtool.services.comparators;

import com.example.projectcalculationtool.models.Project;

import java.util.Comparator;

public class ProjectCompletedComparator implements Comparator<Project> {
    @Override
    public int compare(Project o1, Project o2) {
        return Boolean.compare(o1.isCompleted(),o2.isCompleted());
    }
}
