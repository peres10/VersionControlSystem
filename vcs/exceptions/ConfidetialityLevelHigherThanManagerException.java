package vcs.exceptions;

import vcs.User;

@SuppressWarnings("serial")
public class ConfidetialityLevelHigherThanManagerException extends Exception{

    User user;
    public ConfidetialityLevelHigherThanManagerException(User user){
        this.user=user;
    }

    public int getPMLevel(){
        return user.getClearanceLevel();
    }

}
