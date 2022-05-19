package vcs;

import vcs.exceptions.*;
import vcs.typesOfProjects.*;
import vcs.userPositions.*;
import java.util.*;

public class VersionControlSystemClass implements VersionControlSystem {

    SortedMap<String, User> users;
    Map<String, Project> projects;

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
        return pos.toString();
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
}
