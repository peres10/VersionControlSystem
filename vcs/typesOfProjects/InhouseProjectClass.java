package vcs.typesOfProjects;

import vcs.AbstractProject;
import vcs.userPositions.ProjectManager;

import java.util.Set;

public class InhouseProjectClass extends AbstractProject implements InhouseProject {

    int confidentialityLevel;

    public InhouseProjectClass(String name, ProjectManager pm, Set<String> keywords, int confidentialityLevel) {
        super(name, pm, keywords, "inhouse");
        this.confidentialityLevel =confidentialityLevel;
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
        return 0;
    }
}
