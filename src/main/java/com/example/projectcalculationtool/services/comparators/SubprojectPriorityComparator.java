package com.example.projectcalculationtool.services.comparators;

import com.example.projectcalculationtool.models.Subproject;

import java.util.Comparator;

public class SubprojectPriorityComparator implements Comparator<Subproject> {
    public int compare(Subproject o1, Subproject o2) {
        return Integer.compare(o1.getPriority(), o2.getPriority());
    }
}
