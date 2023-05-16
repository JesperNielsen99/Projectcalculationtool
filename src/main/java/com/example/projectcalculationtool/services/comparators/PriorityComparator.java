package com.example.projectcalculationtool.services.comparators;

import com.example.projectcalculationtool.models.Interfaces.ICompletedAndPriority;

import java.util.Comparator;

public class PriorityComparator implements Comparator<ICompletedAndPriority> {
    public int compare(ICompletedAndPriority o1, ICompletedAndPriority o2) {
        return Integer.compare(o1.getPriority(), o2.getPriority());
    }
}
