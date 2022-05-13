package vcs;

import vcs.exceptions.UnknownJobPositionException;
import vcs.exceptions.UserAlreadyExistsException;

public interface VersionControlSystem {
    String registerUser(String jobPosition, String userName, int clearanceLevel) throws UnknownJobPositionException, UserAlreadyExistsException;
}
