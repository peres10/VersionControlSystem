package vcs.userPositions;

import vcs.AbstractUser;

public class SoftwareDeveloperClass extends AbstractUser implements  SoftwareDeveloper {

    ProjectManager manager;

    public SoftwareDeveloperClass(String username, int clearanceLevel){
        super(username, clearanceLevel);
    }
    @Override
    public String getManagerName() {
        return "abc";  //temporario
        //return manager.getName();
    }
}
