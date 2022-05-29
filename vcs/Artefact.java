package vcs;

import java.time.LocalDate;
import java.util.*;

public interface Artefact {
    String getName();

	int getLevel();
	
	Iterator<Revision> getRevisionsListDescending();

	void addRevision(String projectName, String username, LocalDate date, String comment);

	int getNumberOfRevisions();

	Iterator<Revision> getRevisionsList();
}
