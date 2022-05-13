package vcs.userPositions;

import vcs.AbstractUser;

public class SoftwareDeveloperClass extends AbstractUser implements  SoftwareDeveloper {
    public SoftwareDeveloperClass(String username, int clearanceLevel){
        super(username, clearanceLevel);
    }
}
