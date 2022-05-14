package vcs;

import vcs.userPositions.ProjectManager;

import java.util.ArrayList;

public abstract class AbstractProject implements Project {

    String projectName;
    ProjectManager pm;
    ArrayList<String> keywords;
    String typeOfProject;

    protected AbstractProject(String name, ProjectManager pm, ArrayList<String> keywords, String typeOfProject){
        this.projectName=name;
        this.pm=pm;
        this.keywords=keywords;
        this.typeOfProject=typeOfProject;

    }
}
