package org.sopt.week1;

public class Diary {
	private final Long id;
	private final String body;
	private final boolean isDeleted;

	public Diary(Long id, String body, boolean isDeleted) {
		this.id = id;
		this.body = body;
		this.isDeleted = isDeleted;
	}

	public Long getId() {
		return id;
	}

	public String getBody() {
		return body;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}
}