package org.sopt.diary.service;

import java.util.ArrayList;
import java.util.List;

import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

@Component
public class DiaryService {
	private final DiaryRepository diaryRepository;

	public DiaryService(DiaryRepository diaryRepository) {
		this.diaryRepository = diaryRepository;
	}

	public void createDiary(DiaryDto diaryDto) {
		diaryRepository.save(
				DiaryEntity.toDiaryEntity(diaryDto)
		);
	}

	public List<DiaryDto> getList() {
		// (1) repository 로 부터 DiaryEntity 를 가져옴
		final List<DiaryEntity> diaryEntityList = diaryRepository.findAll();

		// (2) DiaryEntity 를 Diary 로 변환해주는 작업
		final List<DiaryDto> diaryList = new ArrayList<>();

		for (DiaryEntity diaryEntity : diaryEntityList) {
			diaryList.add(
				new DiaryDto(diaryEntity.getId(), diaryEntity.getTitle(), diaryEntity.getContent())
			);
		}

		return diaryList;
	}
}
