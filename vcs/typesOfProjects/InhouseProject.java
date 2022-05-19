package vcs.typesOfProjects;

import vcs.AbstractProject;
import vcs.Project;

public interface InhouseProject extends Project {
    int getLevel();
    int getNumOfArtefacts();
    int getNumOfRevisions();
    int getNumOfMembers();
}
