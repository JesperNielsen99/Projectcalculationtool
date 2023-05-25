package com.example.projectcalculationtool.services.comparators;

import com.example.projectcalculationtool.models.Subproject;

import java.util.Comparator;

public class SubprojectCompletedComparator implements Comparator<Subproject> {

    @Override
    public int compare(Subproject o1, Subproject o2) {
        return Boolean.compare(o1.isCompleted(),o2.isCompleted());
    }
}