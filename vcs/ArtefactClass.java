package vcs;

import java.time.LocalDate;
import java.util.*;

public class ArtefactClass implements Artefact{
    private String ownerName;
    private String artefactName;
    private int confidentialityLevel;
    private String description;
    private List<Revision> revisions;

    public ArtefactClass(String artefactName, int confidentialityLevel, String description, User owner, LocalDate date){
    	this.revisions=new ArrayList<>();
        revisions.add(new RevisionClass(owner.getName(), description, date));
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
	public int getRevisionsSize() {
		return revisions.size();
	}

	@Override
	public void addRevision(String username, LocalDate date, String comment) {
		revisions.add(new RevisionClass(username, comment, date));
	}
}
