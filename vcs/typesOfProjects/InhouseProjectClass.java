package vcs.typesOfProjects;

import vcs.AbstractProject;
import vcs.User;
import vcs.userPositions.ProjectManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InhouseProjectClass extends AbstractProject implements InhouseProject {

    int confidentialityLevel;
    List<User> members;

    public InhouseProjectClass(String name, ProjectManager pm, Set<String> keywords, int confidentialityLevel) {
        super(name, pm, keywords, "inhouse");
        this.confidentialityLevel =confidentialityLevel;
        members = new ArrayList<>();
    }

    @Override
    public int getLevel() {
        return confidentialityLevel;
    }

    @Override
    public int getNumOfArtefacts() {
        return 0;
    }

    @Override
    public int getNumOfRevisions() {
        return 0;
    }

    @Override
    public int getNumOfMembers() {
        return members.size();
    }

    @Override
    public boolean hasMember(User user) {
        return members.contains(user);
    }

    @Override
    public void addTeamMember(User user) {
        members.add(user);
    }
}
