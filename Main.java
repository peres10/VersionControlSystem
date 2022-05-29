import vcs.*;
import vcs.exceptions.*;
import vcs.typesOfProjects.*;
import vcs.userPositions.*;

import java.time.LocalDate;
import java.util.*;

public class Main {

    private enum Feedback {
        EXIT_MSG("Bye!"),
        HEADER_COMMANDS("Available commands:"),
        USER_REGISTERED("User %s was registered as %s with clearance level %d.\n"),
        NO_USERS("No users registered."),
        HEADER_USER_LIST("All registered users:"),
        USER_LIST_FORMAT_MANAGER("manager %s [%d, %d, %d]\n"),
        USER_LIST_FORMAT_DEVELOPER("developer %s is managed by %s [%d]\n"),
        PROJECT_CREATED("%s project was created.\n"),
        NO_PROJECTS("No projects added."),
        HEADER_PROJECTS_LIST("All projects:"),
        PROJECT_LIST_FORMAT_INHOUSE("in-house %s is managed by %s [%d, %d, %d, %d]\n"),
        PROJECT_LIST_FORMAT_OUTSOURCED("outsourced %s is managed by %s and developed by %s\n"),
        HEADER_ADD_TEAM("Latest team members:"),
        ADD_TO_TEAM_FORMAT("%s: %s.\n"),
        HEADER_ARTEFACTS("Latest project artefacts:"),
        HEADER_PM_MANAGES("Manager %s:\n"),
        MANAGES_USER_MANAGED("%s\n"),
        MANAGES_USER_REVISIONS("%s, %s, revision %d, %s, %s\n"),
        ARTEFACTS_FORMAT("%s: %s.\n"),
    	PROJECT_DETAILS_HEADER("%s [%d] managed by %s [%d]:\n"),
    	PROJECT_DETAILS_MEMBERS("%s [%d]\n"),
    	PROJECT_DETAILS_ARTEFACTS("%s [%d]\n"),
    	PROJECT_DETAILS_REVISIONS("revision %d %s %s %s\n"), 
    	REVISION_SUBMITED("Revision %d of artefact %s was submited.\n");

        private final String msg;

        Feedback(String msg) {
            this.msg = msg;
        }

        public String toString() {
            return this.msg;
        }
    }

    private enum Error {
        UNKNOWN_COMMAND("Unknown command. Type help to see available commands.\n"),
        UNKNOWN_JOB_POSITION("Unknown job position."),
        USER_ALREADY_EXISTS("User %s already exists.\n"),
        USER_NOT_EXIST("User %s doest not exist.\n"),
        UNKNOWN_PROJECT_TYPE("Unknown project type."),
        PROJECT_MANAGER_DONT_EXISTS("Project manager %s does not exist.\n"),
        PROJECT_ALREADY_EXISTS("%s project already exists.\n"),
        PROJECT_HAS_HIGHER_CONFIDENTIALITY_THAN_PM("Project manager %s has clearance level %d.\n"),
        PROJECT_NOT_EXIST("%s project does not exist.\n"),
        PROJECT_MANAGED_BY_OTHER_USER("%s is managed by %s.\n"),
        USER_NOT_IN_TEAM("User %s does not belong to the team of %s.\n"),
    	PROJECT_IS_OUTSOURCED("%s is an outsourced project.\n"),
    	ARTEFACT_NOT_EXIST_IN_PROJECT("%s does not exist in project.\n");

        private final String msg;

        Error(String msg) {
            this.msg = msg;
        }

        public String toString() {
            return this.msg;
        }
    }

    private enum Command {
        REGISTER("adds a new user"),
        USERS("lists all registered users"),
        CREATE("creates a new project"),
        PROJECTS("lists all projects"),
        TEAM("adds team members to a project"),
        ARTEFACTS("adds artefacts to a project"),
        PROJECT("shows detailed project information"),
        REVISION("revises an artefact"),
        MANAGES("lists developers of a manager"),
        KEYWORD("filters projects by keyword"),
        CONFIDENTIALITY("filters projects by confidentiality level"),
        WORKAHOLICS("top 3 employees with more artefacts updates"),
        COMMON("employees with more projects in common"),
        HELP("shows the available commands"),
        EXIT("terminates the execution of the program"),
        UNKNOWN("");

        private final String cmdDesc;

        Command(String cmdDesc) {
            this.cmdDesc = cmdDesc;
        }

        private String description() {
            return cmdDesc;
        }
    }

    public static void main(String[] args) {
        commands();
    }

    private static Command getCommand(String comm) {
        try {
            return Command.valueOf(comm);
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }

    }

    private static void commands() {
        VersionControlSystem vSystem = new VersionControlSystemClass();
        Scanner in = new Scanner(System.in);
        String comm = in.next().toUpperCase();
        Command c = getCommand(comm);
        while (!c.equals(Command.EXIT)) {
            switch (c) {
                case REGISTER:
                    registerCommand(in, vSystem);
                    break;
                case USERS:
                    usersCommand(vSystem);
                    break;
                case CREATE:
                    createCommand(in, vSystem);
                    break;
                case PROJECTS:
                    projectsCommand(vSystem);
                    break;
                case TEAM:
                    teamCommand(in,vSystem);
                    break;
                case ARTEFACTS:
                	artefactsCommand(in, vSystem);
                    break;
                case PROJECT:
                    projectCommand(in,vSystem);
                	break;
                case REVISION:
                	revisionCommand(in,vSystem);
                    break;
                case MANAGES:
                	managesCommand(in,vSystem);
                    break;
                case KEYWORD:
                	keywordCommand(in,vSystem);
                    break;
                case CONFIDENTIALITY:
                	confidentialityCommand(in,vSystem);
                	break;
                case WORKAHOLICS:
                	workaholicsCommand(in,vSystem);
                	break;
                case COMMON:
                	commonCommand(in,vSystem);
                    break;
                case HELP:
                    helpCommand();
                    break;
                case UNKNOWN:
                    System.out.printf(Error.UNKNOWN_COMMAND.toString());
                    break;
                default:
                    break;
            }
            comm = in.next().toUpperCase();
            c = getCommand(comm);
        }
        System.out.println(Feedback.EXIT_MSG.toString());
        in.close();
    }

    private static void helpCommand() {
        System.out.println(Feedback.HEADER_COMMANDS.toString());
        for (Command cmd : Command.values()) {
            if (cmd == Command.UNKNOWN)
                return;
            System.out.println(cmd.name().toLowerCase() + " - " + cmd.description());
        }
    }

    private static void registerCommand(Scanner in, VersionControlSystem vSystem) {
        String jobPosition = in.next().trim();
        String name = in.next().trim();
        String pManagerName = null;
        if (jobPosition.equals("developer"))
            pManagerName = in.next().trim();
        int clearanceLevel = in.nextInt();
        in.nextLine();
        try {
            String fullPositionName = vSystem.registerUser(jobPosition, name, clearanceLevel, pManagerName);
            System.out.printf(Feedback.USER_REGISTERED.toString(), name, fullPositionName, clearanceLevel);
        } catch (UnknownJobPositionException e) {
            System.out.println(Error.UNKNOWN_JOB_POSITION.toString());
        } catch (UserAlreadyExistsException e) {
            System.out.printf(Error.USER_ALREADY_EXISTS.toString(), name);
        } catch (ProjectManagerNotExistException e) {
            System.out.printf(Error.PROJECT_MANAGER_DONT_EXISTS.toString(), pManagerName);
        }
    }

    private static void usersCommand(VersionControlSystem vSystem) {
        Iterator<User> usersIt = vSystem.listAllUsers();
        if (!usersIt.hasNext()) {
            System.out.println(Feedback.NO_USERS.toString());
        } else {
            System.out.println(Feedback.HEADER_USER_LIST.toString());
            while (usersIt.hasNext()) {
                User user = usersIt.next();
                if (user instanceof SoftwareDeveloperClass)
                    System.out.printf(Feedback.USER_LIST_FORMAT_DEVELOPER.toString(), user.getName(), ((SoftwareDeveloperClass) user).getManagerName(), user.getNumOfProjectsWorking());
                if (user instanceof ProjectManagerClass)
                    System.out.printf(Feedback.USER_LIST_FORMAT_MANAGER.toString(), user.getName(), ((ProjectManagerClass) user).getNumOfDevelopers(), ((ProjectManagerClass) user).getNumOfProjectsAsManager(), user.getNumOfProjectsWorking());
            }
        }
    }

    private static void createCommand(Scanner in, VersionControlSystem vSystem) {
        String pmUsername = in.next().trim();
        String typeOfProject = in.next().trim();
        String projectName = in.nextLine().trim();
        int numOfKeywords = in.nextInt();
        Set<String> keywords = new HashSet<>();
        for (int i = 0; i < numOfKeywords; i++)
            keywords.add(in.next().trim());
        String nameOfCompanyOrConfidentialityLevel = in.next();
        in.nextLine();

        try {
            vSystem.createProject(pmUsername, typeOfProject, projectName, keywords, nameOfCompanyOrConfidentialityLevel);
            System.out.printf(Feedback.PROJECT_CREATED.toString(), projectName);
        } catch (ProjectTypeUnknownException e) {
            System.out.println(Error.UNKNOWN_PROJECT_TYPE.toString());
        } catch (ProjectManagerNotExistException e) {
            System.out.printf(Error.PROJECT_MANAGER_DONT_EXISTS.toString(), pmUsername);
        } catch (ProjectNameAlreadyExistsException e) {
            System.out.printf(Error.PROJECT_ALREADY_EXISTS.toString(), projectName);
        } catch (ConfidetialityLevelHigherThanManagerException e) {
            System.out.printf(Error.PROJECT_HAS_HIGHER_CONFIDENTIALITY_THAN_PM.toString(), pmUsername, e.getPMLevel());
        }
    }

    private static void projectsCommand(VersionControlSystem vSystem) {
        Iterator<Project> projectsIT = vSystem.listAllProjects();
        if (!projectsIT.hasNext()) {
            System.out.println(Feedback.NO_PROJECTS.toString());
        } else {
            System.out.println(Feedback.HEADER_PROJECTS_LIST.toString());
            while (projectsIT.hasNext()) {
                Project project = projectsIT.next();
                if (project instanceof InhouseProjectClass) {
                    InhouseProject projectIH = (InhouseProject) project;
                    System.out.printf(Feedback.PROJECT_LIST_FORMAT_INHOUSE.toString(), projectIH.getName(), projectIH.getManagerName(), projectIH.getLevel(), projectIH.getNumOfMembers(),projectIH.getNumOfArtefacts(), projectIH.getNumOfRevisions());
                } else if (project instanceof OutsourcedProjectClass) {
                    OutsourcedProject projectOS = (OutsourcedProject) project;
                    System.out.printf(Feedback.PROJECT_LIST_FORMAT_OUTSOURCED.toString(), projectOS.getName(), projectOS.getManagerName(), projectOS.getCompanyName());
                }
            }
        }
    }

    private static void teamCommand(Scanner in,VersionControlSystem vSystem){
        String pmUsername = in.next();
        String projectName = in.nextLine().trim();
        int numOfUsersToAdd = in.nextInt();
        List<String> usernames = new ArrayList<>();
        while(usernames.size()<numOfUsersToAdd){
            usernames.add(in.next().trim());
        }
        in.nextLine();
        try {
            vSystem.checkProject(projectName,pmUsername);
            System.out.println(Feedback.HEADER_ADD_TEAM.toString());
            for(String user : usernames) {
                System.out.printf(Feedback.ADD_TO_TEAM_FORMAT.toString(),user,vSystem.addMemberToTeam(projectName,user));
            }
        }
        catch(ProjectManagerNotExistException e){
            System.out.printf(Error.PROJECT_MANAGER_DONT_EXISTS.toString(),pmUsername);
        }
        catch(ProjectNameNotExistException e){
            System.out.printf(Error.PROJECT_NOT_EXIST.toString(),projectName);
        }
        catch(ProjectManagedByOtherUserException e){
            System.out.printf(Error.PROJECT_MANAGED_BY_OTHER_USER.toString(),projectName,e.manager());
        }
    }

    private static void artefactsCommand(Scanner in, VersionControlSystem vSystem){
        String username = in.next().trim();
        String projectName = in.nextLine().trim();
        String dateString = in.nextLine();
        int numberOfArtefacts = in.nextInt();
        String[] dateSplitted = dateString.split("-", 3);
        LocalDate date = LocalDate.parse(dateSplitted[0] + '-' + dateSplitted[1] + '-' + dateSplitted[2]);
        
        List<String []> artefacts = new ArrayList<String[]>();
        while(artefacts.size()<numberOfArtefacts){
            String name = in.next().trim();
            String confidentiality = in.next().trim();
            String description = in.nextLine().trim();
            artefacts.add(new String[]{name, confidentiality, description});
        }

        try{
            vSystem.checkArtefacts(projectName,username);
            System.out.println(Feedback.HEADER_ARTEFACTS.toString());
            for(String[] artefact : artefacts) {
                System.out.printf(Feedback.ARTEFACTS_FORMAT.toString(),artefact[0],vSystem.addArtefact(projectName,username,date,artefact));
            }
        } 
        catch(UserNotExistException e){
            System.out.printf(Error.USER_NOT_EXIST.toString(),username);
        } 
        catch(ProjectNameNotExistException e){
            System.out.printf(Error.PROJECT_NOT_EXIST.toString(),username);
        } 
        catch(UserNotInTeamException e){
            System.out.printf(Error.USER_NOT_IN_TEAM.toString(),username,projectName);
        }
    }

	private static void projectCommand(Scanner in, VersionControlSystem vSystem) {
		String projectName = in.nextLine().trim();
		Project project;
		Iterator<User> membersIt;
		Iterator<Artefact> artefactsIt;
		Iterator<Revision> revisionsIt;
		User member;
		Artefact artefact;
		Revision revision;
		
		try {
			project = vSystem.getProjectDetails(projectName);
			membersIt = ((InhouseProject)project).getMemberList();
			artefactsIt = ((InhouseProject)project).getArtefactList();
			
			System.out.printf(Feedback.PROJECT_DETAILS_HEADER.toString(), project.getName(), ((InhouseProject)project).getLevel(), project.getManagerName(), project.getProjectManager().getClearanceLevel());
			while(membersIt.hasNext()) {
				member = membersIt.next();
				System.out.printf(Feedback.PROJECT_DETAILS_MEMBERS.toString(), member.getName(), member.getClearanceLevel());
			}
			while(artefactsIt.hasNext()) {
				artefact = artefactsIt.next();
				revisionsIt = artefact.getRevisionsListDescending();
				System.out.printf(Feedback.PROJECT_DETAILS_ARTEFACTS.toString(), artefact.getName(), artefact.getLevel());
				while(revisionsIt.hasNext()) {
					revision = revisionsIt.next();
					System.out.printf(Feedback.PROJECT_DETAILS_REVISIONS.toString(), revision.getRevisionNumber(), revision.getName(), revision.getDate(), revision.getComment());
				}
			}
		}
		catch(ProjectNameNotExistException e){
            System.out.printf(Error.PROJECT_NOT_EXIST.toString(),projectName);
        }
		catch(ProjectIsOutsourcedException e){
            System.out.printf(Error.PROJECT_IS_OUTSOURCED.toString(),projectName);
		}		
		
		
	}

	private static void revisionCommand(Scanner in, VersionControlSystem vSystem) {
		String username = in.next().trim();
		String projectName = in.nextLine().trim();
		String artefactName = in.next().trim();
		String dateString = in.next().trim();
		String comment = in.nextLine().trim();
		String[] dateSplitted = dateString.split("-", 3);
        LocalDate date = LocalDate.parse(dateSplitted[0] + '-' + dateSplitted[1] + '-' + dateSplitted[2]);
        
        try {
        	int revisionNumber = vSystem.addRevisionToArtifactInProject(username, projectName, artefactName, date, comment);
        	System.out.printf(Feedback.REVISION_SUBMITED.toString(), revisionNumber, artefactName);
        }
        catch(UserNotExistException e) {
	        System.out.printf(Error.USER_NOT_EXIST.toString(),username);
	    } 
        catch(ProjectNameNotExistException e) {
	        System.out.printf(Error.PROJECT_NOT_EXIST.toString(),username);
	    } 
        catch(UserNotInTeamException e) {
	        System.out.printf(Error.USER_NOT_IN_TEAM.toString(),username,projectName);
	    }
        catch(ArtefactNotExistInProjectException e) {
        	System.out.printf(Error.ARTEFACT_NOT_EXIST_IN_PROJECT.toString(), artefactName);
        }
		
	}

	private static void managesCommand(Scanner in, VersionControlSystem vSystem) {
		String username = in.nextLine().trim();
		Iterator<SoftwareDeveloper> managedUsersIt;
		Iterator<Revision> revisionsOfUserIt;
		User managedUser;
		Revision revision;
		try {
			vSystem.checkProjectManager(username);
			managedUsersIt = vSystem.getUsersManagedByPM(username);
			
			System.out.printf(Feedback.HEADER_PM_MANAGES.toString(), username);
			while(managedUsersIt.hasNext()) {
				managedUser = (User)managedUsersIt.next();
				System.out.printf(Feedback.MANAGES_USER_MANAGED.toString(), managedUser.getName());
				revisionsOfUserIt = vSystem.getUserRevisions(managedUser);
				while(revisionsOfUserIt.hasNext()) {
					revision = revisionsOfUserIt.next();
					System.out.printf(Feedback.MANAGES_USER_REVISIONS.toString(), revision.getProjectName(), revision.getArtefactName(), revision.getRevisionNumber(), revision.getDate(), revision.getComment());
				}
			}
		}
		catch(UserNotExistException e) {
	        System.out.printf(Error.USER_NOT_EXIST.toString(),username);
	    } 
		catch(ProjectManagerNotExistException e){
	        System.out.printf(Error.USER_NOT_EXIST.toString(),username);
        }
	}

	private static void keywordCommand(Scanner in, VersionControlSystem vSystem) {
		// TODO Auto-generated method stub
		
	}

	private static void confidentialityCommand(Scanner in, VersionControlSystem vSystem) {
		// TODO Auto-generated method stub
		
	}

	private static void workaholicsCommand(Scanner in, VersionControlSystem vSystem) {
		// TODO Auto-generated method stub
		
	}

	private static void commonCommand(Scanner in, VersionControlSystem vSystem) {
		// TODO Auto-generated method stub
		
	}



}
