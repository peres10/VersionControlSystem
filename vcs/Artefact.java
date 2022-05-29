package vcs;

import java.time.LocalDate;
import java.util.*;

public interface Artefact {
    String getName();

	int getLevel();
	
	Iterator<Revision> getRevisionsListDescending();

	int getRevisionsSize();

	void addRevision(String username, LocalDate date, String comment);
}
