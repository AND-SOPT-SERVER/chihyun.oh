package org.sopt.diary.api;

public class DiaryResponse {
	private final long id;
	private final String title;
	private final String content;

	public DiaryResponse(long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
}
