import vcs.*;

import javax.sound.midi.Soundbank;
import java.util.*;

public class Main {

    private static final String EXIT_MSG = "Bye!";
    private static final String ERROR_UNKNOWN_COMMAND = "Unknown command %s. Type help to see available commands.\n";

    private enum Command{

        REGISTER("adds a new user"),
        USERS("lists all registered users"),
        CREATE("creates a new project"),
        PROJECTS("lists all projects"),
        TEAM(" adds team members to a project"),
        ARTEFACTS("adds artefacts to a project"),
        REVISION("revises an artefact"),
        MANAGED("lists developers of a manager"),
        KEYWORD("filters projects by keyword"),
        CONFIDENTIALITY("filters projects by confidentiality level"),
        WORKAHOLICS("top 3 employees with more artefacts updates"),
        COMMON("employees with more projects in common"),
        HELP("shows the available commands"),
        EXIT("terminates the execution of the program"),
        UNKNOWN("");

        private final String cmdDesc;

        Command(String cmdDesc){
            this.cmdDesc=cmdDesc;
        }

        private String description(){
            return cmdDesc;
        }
    }
    public static void main(String[] args) {
        commands();
    }

    private static Command getCommand(String comm){
        try{
            return Command.valueOf(comm);
        }
        catch(IllegalArgumentException e){
            return Command.UNKNOWN;
        }

    }
    private static void commands(){
        VersionControlSystem vSystem = new VersionControlSystemClass();
        Scanner in=new Scanner(System.in);
        String comm = in.next().toUpperCase();
        Command c=getCommand(comm);
        while(!c.equals(Command.EXIT)){
            switch (c){
                case REGISTER:
                    break;
                case USERS:
                    break;
                case CREATE:
                    break;
                case PROJECTS:
                    break;
                case TEAM:
                    break;
                case ARTEFACTS:
                    break;
                case REVISION:
                    break;
                case MANAGED:
                    break;
                case KEYWORD:
                    break;
                case CONFIDENTIALITY:
                    break;
                case WORKAHOLICS:
                    break;
                case COMMON:
                    break;
                case HELP:
                    helpCommands();
                    break;
                case UNKNOWN:
                    System.out.printf(ERROR_UNKNOWN_COMMAND,comm);
                    break;
                default:
                    break;
            }
            comm=in.next().toUpperCase();
            c=getCommand(comm);
        }
        System.out.println(EXIT_MSG);
        in.close();
    }

    private static void helpCommands(){
        for(Command cmd : Command.values()){
            if(cmd==Command.UNKNOWN)
                return;
            System.out.println(cmd.name().toLowerCase()+" - " + cmd.description());
        }
    }
}
