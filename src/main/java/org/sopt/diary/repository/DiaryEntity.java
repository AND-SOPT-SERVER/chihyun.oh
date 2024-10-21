package org.sopt.diary.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.sopt.diary.service.DiaryDto;

@Entity
public class DiaryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column
	public String title;

	@Column
	public String content;

	public DiaryEntity() {
	}

	public DiaryEntity(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public static DiaryEntity toDiaryEntity(DiaryDto diaryDTO) {
		return new DiaryEntity(
				diaryDTO.getTitle(),
				diaryDTO.getContent()
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
