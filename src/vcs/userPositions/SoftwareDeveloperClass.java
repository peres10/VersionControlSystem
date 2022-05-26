package vcs.userPositions;

import vcs.AbstractUser;
import vcs.User;

public class SoftwareDeveloperClass extends AbstractUser implements  SoftwareDeveloper {

    ProjectManager manager;

    public SoftwareDeveloperClass(String username, int clearanceLevel, User pManager){
        super(username, clearanceLevel);
        manager = (ProjectManagerClass)pManager;
    }
    @Override
    public String getManagerName() {
        return manager.getName();
    }
}
