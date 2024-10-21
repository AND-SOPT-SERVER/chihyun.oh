package org.sopt.diary.service;

import org.sopt.diary.api.DiaryRequestDto;

public class DiaryDto {
	private final Long id;
	private final String title;
	private final String content;

	public DiaryDto(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public static DiaryDto toDiaryDto(DiaryRequestDto diaryRequestDto) {
		return new DiaryDto(
				diaryRequestDto.getId(),
				diaryRequestDto.getTitle(),
				diaryRequestDto.getContent()
		);
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
}
