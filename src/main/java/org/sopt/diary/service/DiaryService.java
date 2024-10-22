package org.sopt.diary.service;

import java.util.List;

import org.sopt.diary.dto.Diary;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

@Component
public class DiaryService {
	private final DiaryRepository diaryRepository;

	public DiaryService(DiaryRepository diaryRepository) {
		this.diaryRepository = diaryRepository;
	}

	public void createDiary(Diary diary) {
		diaryRepository.save(
				DiaryEntity.toDiaryEntity(diary)
		);
	}

	public List<Diary> getList() {
		return diaryRepository.findTop10ByOrderByCreatedAtDesc().stream()
				.map(Diary::toDiaryDto)
				.toList();
	}
}
