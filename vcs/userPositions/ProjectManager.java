package vcs.userPositions;

import java.util.Iterator;

import vcs.Project;
import vcs.User;

public interface ProjectManager extends User {
    int getNumOfDevelopers();
    int getNumOfProjectsAsManager();
    void addManagingProject(Project project);
    void addDeveloperToManager(SoftwareDeveloper developer);
    boolean managesDeveloper(SoftwareDeveloper developer);
	Iterator<SoftwareDeveloper> listUsersManaged();
}
