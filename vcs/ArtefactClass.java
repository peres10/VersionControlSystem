package vcs;

import java.util.*;

public class ArtefactClass implements Artefact{
    private User owner;
    private String name;
    private int confidentialityLevel;
    private String description;
    private List<Revision> revisions;

    public ArtefactClass(String artefactName, int confidentialityLevel, String description, User owner){
        this.name=artefactName;
        this.confidentialityLevel=confidentialityLevel;
        this.description=description;
        this.owner=owner;
        this.revisions=new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
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
}
