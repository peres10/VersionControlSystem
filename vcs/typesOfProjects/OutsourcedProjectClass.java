package vcs.typesOfProjects;

import vcs.AbstractProject;
import vcs.userPositions.ProjectManager;

import java.util.ArrayList;

public class OutsourcedProjectClass extends AbstractProject {

    String company;

    public OutsourcedProjectClass(String name, ProjectManager pm, ArrayList<String> keywords, String typeOfProject,String company) {
        super(name, pm, keywords, typeOfProject);
        this.company=company;
    }
}
