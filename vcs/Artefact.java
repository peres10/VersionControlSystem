package vcs;

import java.util.*;

public interface Artefact {
    String getName();

	int getLevel();
	
	Iterator<Revision> getRevisionsListDescending();

	int getRevisionsSize();
}
