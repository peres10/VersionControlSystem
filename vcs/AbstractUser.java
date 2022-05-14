package vcs;

public abstract class AbstractUser implements User {
    private String username;
    //private String jobPosition;
    private int clearanceLevel;
    private int numberOfProjectsWorking;

    protected AbstractUser(String username, int clearanceLevel) {
        this.username = username;
        //this.jobPosition = jobPosition;
        this.clearanceLevel = clearanceLevel;
        this.numberOfProjectsWorking=0;
    }

    public String getName(){
        return username;
    }

    public int getNumOfProjectsWorking(){
        return numberOfProjectsWorking;
    }

    public int getClearanceLevel(){
        return clearanceLevel;
    }

}
