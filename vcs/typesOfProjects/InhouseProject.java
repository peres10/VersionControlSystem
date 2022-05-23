package vcs.typesOfProjects;

import vcs.AbstractProject;
import vcs.Project;
import vcs.User;

public interface InhouseProject extends Project {
    int getLevel();
    int getNumOfArtefacts();
    int getNumOfRevisions();
    int getNumOfMembers();
    boolean hasMember(User user);
    void addTeamMember(User user);
}
