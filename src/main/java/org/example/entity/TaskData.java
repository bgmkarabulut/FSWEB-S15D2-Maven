package org.example.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskData {
    private Set<Task> annsTasks;
    private Set<Task> bobsTasks;
    private Set<Task> carolsTasks;
    private Set<Task> unassignedTasks;

    public TaskData(Set<Task> annsTasks, Set<Task> bobsTasks, Set<Task> carolsTasks, Set<Task> unassignedTasks) {
        this.annsTasks = annsTasks;
        this.bobsTasks = bobsTasks;
        this.carolsTasks = carolsTasks;
        this.unassignedTasks = unassignedTasks;
    }

    public Set<Task> getTasks(String assignee) {
        switch (assignee.toLowerCase()) {
            case "ann":
                return annsTasks;
            case "bob":
                return bobsTasks;
            case "carol":
                return carolsTasks;
            case "all":
                return getUnion(Arrays.asList(annsTasks, bobsTasks, carolsTasks));
            default:
                return Collections.emptySet();
        }
    }

    public Set<Task> getUnassignedTasks() {
        return unassignedTasks;
    }

    public Set<Task> getUnion(List<Set<Task>> sets) {
        return sets.stream().flatMap(Set::stream).collect(Collectors.toSet());
    }

    public Set<Task> getIntersection(Set<Task> set1, Set<Task> set2) {
        return set1.stream().filter(set2::contains).collect(Collectors.toSet());
    }

    public Set<Task> getDifferences(Set<Task> set1, Set<Task> set2) {
        return set1.stream().filter(task -> !set2.contains(task)).collect(Collectors.toSet());
    }

    public Set<Task> getTasksAssignedToMultiple() {
        Set<Task> allTasks = getUnion(Arrays.asList(annsTasks, bobsTasks, carolsTasks));
        return allTasks.stream()
                .filter(task -> (
                        (annsTasks.contains(task) ? 1 : 0) +
                                (bobsTasks.contains(task) ? 1 : 0) +
                                (carolsTasks.contains(task) ? 1 : 0)) > 1)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "TaskData{" +
                "annsTasks=" + annsTasks +
                ", bobsTasks=" + bobsTasks +
                ", carolsTasks=" + carolsTasks +
                ", unassignedTasks=" + unassignedTasks +
                '}';
    }

    public Set<Task> getUnion(Set<Task> taskSet, Set<Task> taskSet2) {
        return getUnion(taskSet,taskSet2);
    }
}
