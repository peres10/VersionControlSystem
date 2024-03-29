package vcs;

import java.util.*;

import vcs.typesOfProjects.InhouseProject;

public abstract class AbstractUser implements User {
    private String username;
    //private String jobPosition;
    private int clearanceLevel;
    List<Project> projectsWorking;

    protected AbstractUser(String username, int clearanceLevel) {
        this.username = username;
        //this.jobPosition = jobPosition;
        this.clearanceLevel = clearanceLevel;
        projectsWorking=new ArrayList<>();
    }

    @Override
    public String getName(){
        return username;
    }
    
    @Override
    public int getNumOfProjectsWorking(){
        return projectsWorking.size();
    }
    
    @Override
    public int getClearanceLevel(){
        return clearanceLevel;
    }

    @Override
    public void addProject(Project project){
        projectsWorking.add(project);
    }

	public Iterator<Project> listProjectsWorking(){
		return projectsWorking.iterator();
	}

}
