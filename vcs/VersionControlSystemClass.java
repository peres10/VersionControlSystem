package vcs;

import vcs.exceptions.UnknownJobPositionException;
import vcs.exceptions.UserAlreadyExistsException;
import vcs.userPositions.ProjectManagerClass;
import vcs.userPositions.SoftwareDeveloperClass;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class VersionControlSystemClass implements VersionControlSystem {

    SortedMap<String, User> users;

    private enum Position {
        DEVELOPER("software developer"),
        MANAGER("project manager");

        private final String positionName;

        Position(String positionName) {
            this.positionName = positionName;
        }

        public String toString() {
            return this.positionName;
        }
    }

    public VersionControlSystemClass() {
        users = new TreeMap<>();
    }

    @Override
    public String registerUser(String jobPosition, String userName, int clearanceLevel) throws UnknownJobPositionException, UserAlreadyExistsException {
        Position pos;
        try {
            pos = Position.valueOf(jobPosition.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownJobPositionException();
        }
        if (users.containsKey(userName))
            throw new UserAlreadyExistsException();

        switch (pos) {
            case DEVELOPER: {
                users.put(userName, new SoftwareDeveloperClass(userName, clearanceLevel));
                break;
            }
            case MANAGER: {
                users.put(userName, new ProjectManagerClass(userName, clearanceLevel));
                break;
            }
        }
        return pos.toString();
    }

    @Override
    public Iterator<User> listAllUsers() {
        return users.values().iterator();
    }
}
