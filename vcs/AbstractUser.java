package vcs;

import java.util.ArrayList;
import java.util.List;

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

    public String getName(){
        return username;
    }

    public int getNumOfProjectsWorking(){
        return projectsWorking.size();
    }

    public int getClearanceLevel(){
        return clearanceLevel;
    }

}
