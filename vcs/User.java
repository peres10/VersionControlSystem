package vcs;

import java.util.Iterator;

import vcs.typesOfProjects.InhouseProject;

public interface User {
    String getName();

    int getNumOfProjectsWorking();

    int getClearanceLevel();

    void addProject(Project project);

	Iterator<Project> listProjectsWorking();
}
