package com.example.projectcalculationtool.services.comparators;

import com.example.projectcalculationtool.models.interfaces.ICompletedAndPriority;

import java.util.Comparator;

public class CompletedComparator implements Comparator<ICompletedAndPriority> {
    public int compare(ICompletedAndPriority o1, ICompletedAndPriority o2){
        return Boolean.compare(o1.isCompleted(), o2.isCompleted());
    }
}