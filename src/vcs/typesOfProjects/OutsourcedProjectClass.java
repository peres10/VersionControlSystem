package vcs.typesOfProjects;

import vcs.AbstractProject;
import vcs.userPositions.ProjectManager;

import java.util.Set;

public class OutsourcedProjectClass extends AbstractProject implements OutsourcedProject {

    String company;

    public OutsourcedProjectClass(String name, ProjectManager pm, Set<String> keywords, String company) {
        super(name, pm, keywords, "outsourced");
        this.company=company;
    }

    @Override
    public String getCompanyName() {
        return company;
    }
}
