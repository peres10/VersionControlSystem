package vcs;

import java.time.LocalDate;
import java.util.*;

public class ArtefactClass implements Artefact{
    private String ownerName;
    private String artefactName;
    private int confidentialityLevel;
    private String description;
    private List<Revision> revisions;
    private int numberOfRevisions;
    
    public ArtefactClass(String projectName, String artefactName, int confidentialityLevel, String description, User owner, LocalDate date){
    	this.revisions=new ArrayList<>();
    	this.numberOfRevisions = 1;
    	revisions.add(new RevisionClass(projectName, artefactName, owner.getName(), description, date, this.numberOfRevisions));
    	this.numberOfRevisions++;
    	this.artefactName=artefactName;
        this.confidentialityLevel=confidentialityLevel;
        this.description=description;  
    }

    @Override
    public String getName() {
        return artefactName;
    }

	@Override
	public int getLevel() {
		return confidentialityLevel;
	}
	
	public Iterator<Revision> getRevisionsListDescending() {
		List<Revision> revisionsTemp = new ArrayList<>();
		for(int i = 0; i < revisions.size(); i++) {
			revisionsTemp.add(0, revisions.get(i));
		}
		return revisionsTemp.iterator();
	}

	@Override
	public void addRevision(String projectName, String username, LocalDate date, String comment) {
		revisions.add(new RevisionClass(projectName, this.artefactName, username, comment, date, this.numberOfRevisions));
		numberOfRevisions++;
	}

	@Override
	public int getNumberOfRevisions() {
		return this.numberOfRevisions;
	}

	@Override
	public Iterator<Revision> getRevisionsList() {
		return revisions.iterator();
	}
}
