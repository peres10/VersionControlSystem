package vcs.userPositions;

import vcs.Project;
import vcs.User;

public interface ProjectManager extends User {
    int getNumOfDevelopers();
    int getNumOfProjectsAsManager();
    void addManagingProject(Project project);
    void addDeveloperToManager(SoftwareDeveloper developer);
    boolean managesDeveloper(SoftwareDeveloper developer);
}
