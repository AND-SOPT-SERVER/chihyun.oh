package org.sopt.week1;

import org.sopt.week1.DiaryRepository;

public class DiaryService {
	private final DiaryRepository diaryRepository = new DiaryRepository();

	public void writeDiary(final String body) {
		Diary diary = new Diary(null, body.trim());

		diaryRepository.save(diary);
	}
}