package vcs;

import vcs.userPositions.ProjectManager;

import java.util.Set;

public abstract class AbstractProject implements Project {

    String projectName;
    protected ProjectManager pm;
    Set<String> keywords;
    String typeOfProject;

    protected AbstractProject(String name, ProjectManager pm, Set<String> keywords, String typeOfProject){
        this.projectName=name;
        this.pm=pm;
        this.keywords=keywords;
        this.typeOfProject=typeOfProject;

    }

    public String getName(){
        return projectName;
    }

    public String getManagerName(){
        return pm.getName();
    }
    
    public User getProjectManager() {
    	return pm;
    }
}
