package vcs.typesOfProjects;

import vcs.AbstractProject;
import vcs.Artefact;
import vcs.ArtefactClass;
import vcs.User;
import vcs.userPositions.ProjectManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class InhouseProjectClass extends AbstractProject implements InhouseProject {

    int confidentialityLevel;
    List<User> members;
    List<Artefact> artefacts;

    public InhouseProjectClass(String name, ProjectManager pm, Set<String> keywords, int confidentialityLevel) {
        super(name, pm, keywords, "inhouse");
        this.confidentialityLevel =confidentialityLevel;
        members = new ArrayList<>();
        artefacts = new ArrayList<>();
    }

    @Override
    public int getLevel() {
        return confidentialityLevel;
    }

    @Override
    public int getNumOfArtefacts() {
        return 0;
    }

    @Override
    public int getNumOfRevisions() {
        return 0;
    }

    @Override
    public int getNumOfMembers() {
        return members.size();
    }

    @Override
    public boolean hasMember(User user) {
        return (members.contains(user) || pm.equals(user)) ;
    }

    @Override
    public void addTeamMember(User user) {
        members.add(user);
    }

    @Override
    public boolean hasArtefact(String artefactName) {
        for(Artefact a : artefacts) {
            if (a.getName().equals(artefactName))
                return true;
        }
        return false;
    }

    @Override
    public void addArtefacts(String[] artefact, User owner, LocalDate date) {
        artefacts.add(new ArtefactClass(artefact[0],Integer.parseInt(artefact[1]),artefact[2],owner, date));
    }

	@Override
	public Iterator<User> getMemberList() {
		return members.iterator();
	}

	@Override
	public Iterator<Artefact> getArtefactList() {
		return artefacts.iterator();
	}

	@Override
	public void addRevisionInArtefact(String artefactName, String username, LocalDate date, String comment) {
		for(Artefact a : artefacts) {
            if (a.getName().equals(artefactName))
                a.addRevision(username, date, comment);
        }
	}
	
	
}
