package vcs.typesOfProjects;

import vcs.AbstractProject;
import vcs.userPositions.ProjectManager;

import java.util.ArrayList;

public class InhouseProjectClass extends AbstractProject {

    int confidentialityLevel;

    public InhouseProjectClass(String name, ProjectManager pm, ArrayList<String> keywords, String typeOfProject,int confidentialityLevel) {
        super(name, pm, keywords, typeOfProject);
        this.confidentialityLevel =confidentialityLevel;
    }
}
