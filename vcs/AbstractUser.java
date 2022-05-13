package vcs;

public abstract class AbstractUser implements User {
    private String username;
    //private String jobPosition;
    private int clearanceLevel;

    protected AbstractUser(String username, int clearanceLevel) {
        this.username = username;
        //this.jobPosition = jobPosition;
        this.clearanceLevel = clearanceLevel;
    }
}
