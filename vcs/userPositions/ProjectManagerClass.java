package vcs.userPositions;

import vcs.AbstractUser;
import vcs.Project;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProjectManagerClass extends AbstractUser implements ProjectManager {

    int numOfDeveloperManaged;
    int numOfProjectsAsManager;
    List<SoftwareDeveloper> developersManaged;
    List<Project> projectsAsManager;

    public ProjectManagerClass(String username, int clearanceLevel){
        super(username, clearanceLevel);
        developersManaged=new ArrayList<>();
        projectsAsManager=new ArrayList<>();
    }

    @Override
    public int getNumOfDevelopers() {
        return developersManaged.size();
    }

    @Override
    public int getNumOfProjectsAsManager() {
        return projectsAsManager.size();
    }


    @Override
    public void addDeveloperToManager(SoftwareDeveloper developer) {
        developersManaged.add(developer);
    }

    @Override
    public void addManagingProject(Project project) {
        projectsAsManager.add(project);
    }
}
