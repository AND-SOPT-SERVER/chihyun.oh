package org.sopt.week1;

import java.util.List;

import org.sopt.week1.DiaryRepository;

public class DiaryService {
	private final DiaryRepository diaryRepository = new DiaryRepository();

	void writeDiary(final String body) {
		Diary diary = new Diary(null, body.trim());

		diaryRepository.save(diary);
	}

	List<Diary> getDiaryList() {
		return diaryRepository.findAll();
	}
}