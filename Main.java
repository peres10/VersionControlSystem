import vcs.*;
import vcs.exceptions.UnknownJobPositionException;
import vcs.exceptions.UserAlreadyExistsException;

import java.util.*;

public class Main {

    //private static final String EXIT_MSG = "Bye!";

    private enum Feedback {
        EXIT_MSG("Bye!"),
        USER_REGISTERED("User %s was registered as %s with clearance level %d.\n");

        private final String msg;

        Feedback(String msg) {
            this.msg = msg;
        }

        public String toString() {
            return this.msg;
        }
    }

    private enum Error {
        UNKNOWN_COMMAND("Unknown command %s. Type help to see available commands.\n"),
        UNKNOWN_JOB_POSITION("Unknown job position."),
        USER_ALREADY_EXISTS("User %s already exists\n");

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
                    registerCommand(in,vSystem);
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
                    helpCommand();
                    break;
                case UNKNOWN:
                    System.out.printf(Error.UNKNOWN_COMMAND.msg, comm);
                    break;
                default:
                    break;
            }
            comm = in.next().toUpperCase();
            c = getCommand(comm);
        }
        System.out.println(Feedback.EXIT_MSG.msg);
        in.close();
    }

    private static void helpCommand() {
        for (Command cmd : Command.values()) {
            if (cmd == Command.UNKNOWN)
                return;
            System.out.println(cmd.name().toLowerCase() + " - " + cmd.description());
        }
    }

    private static void registerCommand(Scanner in, VersionControlSystem vSystem) {
        String jobPosition = in.next().trim();
        String name = in.next().trim();
        int clearanceLevel = in.nextInt();
        in.nextLine();
        try {
            String fullPositionName = vSystem.registerUser(jobPosition, name, clearanceLevel);
            System.out.printf(Feedback.USER_REGISTERED.msg, name,fullPositionName, clearanceLevel);
        } catch (UnknownJobPositionException e) {
            System.out.println(Error.UNKNOWN_JOB_POSITION.msg);
        } catch (UserAlreadyExistsException e) {
            System.out.printf(Error.USER_ALREADY_EXISTS.msg,name);

        }
    }

}
