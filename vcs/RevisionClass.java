package vcs;

import java.time.*;

public class RevisionClass implements Revision {
	private String projectName;
	private String artefactName;
	private String memberName;
	private String comment;
	private LocalDate date;
	private int revisionNumber;
	
	public RevisionClass(String projectName, String artefactName, String memberName, String comment, LocalDate date, int revisionNumber) {
		this.projectName = projectName;
		this.artefactName = artefactName;
		this.memberName = memberName;
		this.comment = comment;
		this.date = date;
		this.revisionNumber = revisionNumber;
	}
	
	
	
	@Override
	public String getName() {
		return this.memberName;
	}

	@Override
	public LocalDate getDate() {
		return this.date;
	}

	@Override
	public String getComment() {
		return this.comment;
	}

	@Override
	public int getRevisionNumber() {
		return revisionNumber;
	}



	@Override
	public String getProjectName() {
		return this.projectName;
	}



	@Override
	public String getArtefactName() {
		return this.artefactName;
	}
}
