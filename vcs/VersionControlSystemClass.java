package vcs;

import vcs.exceptions.*;
import vcs.typesOfProjects.*;
import vcs.userPositions.*;

import java.time.LocalDate;
import java.util.*;

public class VersionControlSystemClass implements VersionControlSystem {

    SortedMap<String, User> users;
    Map<String, Project> projects;

    private enum Position {
        DEVELOPER,
        MANAGER;
    }

    private enum ProjectType {
        INHOUSE,
        OUTSOURCED;
    }


    public VersionControlSystemClass() {
        users = new TreeMap<>();
        projects = new LinkedHashMap<>();
    }

    @Override
    public String registerUser(String jobPosition, String userName, int clearanceLevel,String pManagerName) throws UnknownJobPositionException, UserAlreadyExistsException,ProjectManagerNotExistException {
        Position pos;
        try {
            pos = Position.valueOf(jobPosition.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownJobPositionException();
        }
        if (users.containsKey(userName))
            throw new UserAlreadyExistsException();
        if (jobPosition.equals(Position.DEVELOPER.name().toLowerCase()) && (!users.containsKey(pManagerName) || !(users.get(pManagerName) instanceof ProjectManagerClass)))
            throw new ProjectManagerNotExistException();

        switch (pos) {
            case DEVELOPER: {
                SoftwareDeveloper developer = new SoftwareDeveloperClass(userName, clearanceLevel,users.get(pManagerName));
                users.put(userName,(User)developer);
                ((ProjectManager) users.get(pManagerName)).addDeveloperToManager(developer);
                break;
            }
            case MANAGER: {
                users.put(userName, new ProjectManagerClass(userName, clearanceLevel));
                break;
            }
        }
        return pos.name().toLowerCase();
    }

    @Override
    public Iterator<User> listAllUsers() {
        return users.values().iterator();
    }

    @Override
    public void createProject(String pmUsername, String typeOfProject, String projectName, Set<String> keywords, String nameOfCompanyOrConfidentialityLevel) throws ProjectTypeUnknownException, ProjectManagerNotExistException, ProjectNameAlreadyExistsException, ConfidetialityLevelHigherThanManagerException {
        ProjectType pType;
        try{
            pType=ProjectType.valueOf(typeOfProject.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new ProjectTypeUnknownException();
        }
        if (!users.containsKey(pmUsername) || !(users.get(pmUsername) instanceof ProjectManagerClass))
            throw new ProjectManagerNotExistException();
        if(projects.containsKey(projectName))
            throw new ProjectNameAlreadyExistsException();

        switch (pType) {
            case INHOUSE:
                int confidentialityLevel = Integer.parseInt(nameOfCompanyOrConfidentialityLevel);
                createInhouseProject(projectName,pmUsername,keywords,confidentialityLevel);
                break;
            case OUTSOURCED:
                createOutSourcedProject(projectName,pmUsername,keywords,nameOfCompanyOrConfidentialityLevel);
                break;
        }
    }

    @Override
    public Iterator<Project> listAllProjects() {
        return projects.values().iterator();
    }

    @Override
    public void checkProject(String projectName, String pmUsername) throws ProjectManagerNotExistException, ProjectNameNotExistException, ProjectManagedByOtherUserException {
        if(!users.containsKey(pmUsername) || !((users.get(pmUsername) instanceof ProjectManager)))
            throw new ProjectManagerNotExistException();
        if(!projects.containsKey(projectName) || (!(projects.get(projectName) instanceof InhouseProject)))
            throw new ProjectNameNotExistException();
        if(!projects.get(projectName).getManagerName().equals(pmUsername))
            throw new ProjectManagedByOtherUserException(projects.get(projectName).getManagerName());
    }

    @Override
    public String addMemberToTeam(String projectName, String user) {
        if(!users.containsKey(user))
            return("does not exist");
        if(users.get(user).getClearanceLevel()<((InhouseProject)projects.get(projectName)).getLevel())
            return("insufficient clearance level");
        if(((InhouseProject)projects.get(projectName)).hasMember(users.get(user)) || user.equals(projects.get(projectName).getManagerName()))
            return("already a member");
        ((InhouseProject)projects.get(projectName)).addTeamMember(users.get(user));
        users.get(user).addProject(projects.get(projectName));
        return ("added to the team");
    }

    @Override
    public void checkArtefacts(String projectName, String username) throws UserNotExistException, ProjectNameNotExistException, UserNotInTeamException {
        if(!users.containsKey(username))
            throw new UserNotExistException();
        if(!projects.containsKey(projectName))
            throw new ProjectNameNotExistException();
        if(!((InhouseProject)(projects.get(projectName))).hasMember(users.get(username)))
            throw new UserNotInTeamException();
    }

    @Override
    public String addArtefact(String projectName,String username,LocalDate date, String[] artefact) {
        if(((InhouseProject)(projects.get(projectName))).hasArtefact(artefact[0]))
            return "already in the project";
        if((((InhouseProject) (projects.get(projectName))).getLevel() < Integer.parseInt(artefact[1])))
            return "exceeds project confidentiality level";
        ((InhouseProject)(projects.get(projectName))).addArtefacts(artefact, users.get(username), date);
            return "added to the project";
    }


    //private methods
    private void createInhouseProject(String projectName,String pmUserName,Set<String> keywords,int confidentialityLevel) throws ConfidetialityLevelHigherThanManagerException {
        ProjectManager pmManager = (ProjectManager) users.get(pmUserName);
        if(confidentialityLevel > pmManager.getClearanceLevel())
            throw new ConfidetialityLevelHigherThanManagerException(pmManager);
        Project project = new InhouseProjectClass(projectName,pmManager,keywords,confidentialityLevel);
        projects.put(projectName,project);
        pmManager.addManagingProject(project);
    }

    private void createOutSourcedProject(String projectName,String pmUserName,Set<String> keywords, String companyName){
        ProjectManager pmManager = (ProjectManager) users.get(pmUserName);
        Project project = new OutsourcedProjectClass(projectName,pmManager,keywords,companyName);
        projects.put(projectName,project);
        pmManager.addManagingProject(project);
    }

	@Override
	public Project getProjectDetails(String projectName) throws ProjectNameNotExistException, ProjectIsOutsourcedException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addRevisionToArtifactInProject(String username, String projectName, String artefactName, LocalDate date, String comment) throws UserNotExistException, ProjectNameNotExistException, UserNotInTeamException, ArtefactNotExistInProjectException{
		Artefact artefact = null;
		return artefact.getNumberOfRevisions();
	}

	@Override
	public void checkProjectManager(String username) throws UserNotExistException, ProjectManagerNotExistException{
		if(!users.containsKey(username))
            throw new UserNotExistException();
		if(!users.containsKey(username) || !((users.get(username) instanceof ProjectManager)))
            throw new ProjectManagerNotExistException();
        		
	}

	@Override
	public Iterator<SoftwareDeveloper> getUsersManagedByPM(String username) {
		ProjectManager projectManager = (ProjectManager)users.get(username);
		return projectManager.listUsersManaged();
	}

	@Override
	public Iterator<Revision> getUserRevisions(User managedUser) {
		Iterator<Project> userProjectsIt = managedUser.listProjectsWorking();
		Iterator<Artefact> artefactsInProjectIt;
		Iterator<Revision> revisionsIt;
		InhouseProject project;
		Artefact artefact;
		Revision revision;
		List<Revision> userRevisions = new ArrayList<>();
		
		while(userProjectsIt.hasNext()) {
			project = (InhouseProject)userProjectsIt.next();
			artefactsInProjectIt = project.getArtefactList();
			
			while(artefactsInProjectIt.hasNext()) {
				artefact = artefactsInProjectIt.next();
				revisionsIt = artefact.getRevisionsList();
				
				while(revisionsIt.hasNext()) {
					revision = revisionsIt.next();
					
					if(revision.getName().equals(managedUser.getName()))
						userRevisions.add(revision);
				}
			}
		}
		return userRevisions.iterator();
	}

	
}
