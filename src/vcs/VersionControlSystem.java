package vcs;

import vcs.exceptions.*;

import java.util.Iterator;
import java.util.Set;

public interface VersionControlSystem {
    String registerUser(String jobPosition, String userName, int clearanceLevel, String pManagerName) throws UnknownJobPositionException, UserAlreadyExistsException,ProjectManagerNotExistException;

    Iterator<User> listAllUsers();

    void createProject(String pmUsername, String typeOfProject, String projectName, Set<String> keywords, String nameOfCompanyOrConfidentialityLevel) throws ProjectTypeUnknownException, ProjectManagerNotExistException, ProjectNameAlreadyExistsException, ConfidetialityLevelHigherThanManagerException;

    Iterator<Project> listAllProjects();

    void checkProject(String projectName, String pmUsername) throws ProjectManagerNotExistException, ProjectNameNotExistException, ProjectManagedByOtherUserException;

    String addMemberToTeam(String projectName, String user);
}
