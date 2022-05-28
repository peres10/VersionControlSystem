package vcs;

import java.time.*;

public class RevisionClass implements Revision {
	private String memberName;
	private String comment;
	private LocalDate date;
	
	public RevisionClass(String memberName, String comment, LocalDate date) {
		this.memberName = memberName;
		this.comment = comment;
		this.date = date;
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
}
