package org.sopt.diary.dto;

import java.time.LocalDateTime;

public class DiaryResponse {
	private final long id;
	private final String title;
	private final String content;
	private final LocalDateTime createdAt;

	private DiaryResponse(final long id, final String title) {
		this.title = title;
		this.id = id;
		this.content = null;
		this.createdAt = null;
	}

	private DiaryResponse(final long id, final String title, final String content, final LocalDateTime createdAt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
	}

	public static DiaryResponse toDiaryResponse(final Diary diary) {
		return new DiaryResponse(
				diary.getId(),
				diary.getTitle()
		);
	}

	public static DiaryResponse toDiaryDetailResponse(final Diary diary) {
		return new DiaryResponse(
				diary.getId(),
				diary.getTitle(),
				diary.getContent(),
				diary.getCreatedAt()
		);
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
