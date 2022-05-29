package vcs;

import java.time.LocalDate;

public interface Revision {

	String getName();

	LocalDate getDate();

	String getComment();
	
	int getRevisionNumber();

	String getProjectName();

	String getArtefactName();
}
