package vcs.userPositions;

import vcs.Project;
import vcs.User;

public interface ProjectManager extends User {
    int getNumOfDevelopers();
    int getNumOfProjectsAsManager();
    void addDeveloperToManager(SoftwareDeveloper developer);
    void addManagingProject(Project project);


}
