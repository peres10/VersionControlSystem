package vcs.exceptions;
@SuppressWarnings("serial")
public class ProjectManagedByOtherUserException extends Exception {

    String pmUsername;
    public ProjectManagedByOtherUserException(String pmUsername){
        super();
        this.pmUsername=pmUsername;
    }

    public String manager(){
        return pmUsername;
    }
}
