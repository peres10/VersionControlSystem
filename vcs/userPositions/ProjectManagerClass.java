package vcs.userPositions;

import vcs.AbstractUser;

public class ProjectManagerClass extends AbstractUser implements ProjectManager {

    int numOfDeveloperManaged;
    int numOfProjectsAsManager;

    public ProjectManagerClass(String username, int clearanceLevel){
        super(username, clearanceLevel);
        numOfDeveloperManaged=0;
        numOfProjectsAsManager=0;
    }

    @Override
    public int getNumOfDevelopers() {
        return 0;
    }

    @Override
    public int getNumOfProjectsAsManager() {
        return 0;
    }
}
