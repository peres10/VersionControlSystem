package vcs.typesOfProjects;

import java.util.Iterator;
import vcs.Artefact;
import vcs.Project;
import vcs.User;

public interface InhouseProject extends Project {
    int getLevel();
    int getNumOfArtefacts();
    int getNumOfRevisions();
    int getNumOfMembers();
    boolean hasMember(User user);
    void addTeamMember(User user);
    boolean hasArtefact(String artefactName);
    void addArtefacts(String[] artefact,User owner);
	Iterator<User> getMemberList();
	Iterator<Artefact> getArtefactList();
}
