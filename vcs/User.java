package vcs;

public interface User {
    String getName();

    int getNumOfProjectsWorking();

    int getClearanceLevel();

    void addProject(Project project);
}
