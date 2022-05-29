package vcs;

import vcs.exceptions.*;
import vcs.userPositions.SoftwareDeveloper;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

public interface VersionControlSystem {
    String registerUser(String jobPosition, String userName, int clearanceLevel, String pManagerName) throws UnknownJobPositionException, UserAlreadyExistsException,ProjectManagerNotExistException;

    Iterator<User> listAllUsers();

    void createProject(String pmUsername, String typeOfProject, String projectName, Set<String> keywords, String nameOfCompanyOrConfidentialityLevel) throws ProjectTypeUnknownException, ProjectManagerNotExistException, ProjectNameAlreadyExistsException, ConfidetialityLevelHigherThanManagerException;

    Iterator<Project> listAllProjects();

    void checkProject(String projectName, String pmUsername) throws ProjectManagerNotExistException, ProjectNameNotExistException, ProjectManagedByOtherUserException;

    String addMemberToTeam(String projectName, String user);

    void checkArtefacts(String projectName, String username) throws  UserNotExistException,ProjectNameNotExistException,UserNotInTeamException;

    String addArtefact(String projectName, String username, LocalDate date ,String[] artefact);

	Project getProjectDetails(String projectName) throws ProjectNameNotExistException, ProjectIsOutsourcedException;

	int addRevisionToArtifactInProject(String username, String projectName, String artefactName, LocalDate date,
			String comment) throws UserNotExistException, ProjectNameNotExistException, UserNotInTeamException, ArtefactNotExistInProjectException;

	void checkProjectManager(String username) throws UserNotExistException, ProjectManagerNotExistException;

	Iterator<SoftwareDeveloper> getUsersManagedByPM(String username);

	Iterator<Revision> getUserRevisions(User managedUser); 
}
