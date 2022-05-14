package vcs;

import vcs.exceptions.UnknownJobPositionException;
import vcs.exceptions.UserAlreadyExistsException;

import java.util.Iterator;

public interface VersionControlSystem {
    String registerUser(String jobPosition, String userName, int clearanceLevel) throws UnknownJobPositionException, UserAlreadyExistsException;

    Iterator<User> listAllUsers();
}
