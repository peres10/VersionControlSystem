package vcs.userPositions;

import vcs.AbstractUser;

public class ProjectManagerClass extends AbstractUser implements ProjectManager {
    public ProjectManagerClass(String username, int clearanceLevel){
        super(username, clearanceLevel);
    }
}
