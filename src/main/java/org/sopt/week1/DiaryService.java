package org.sopt.week1;

import java.util.List;

import org.sopt.week1.DiaryRepository;

public class DiaryService {
	private final DiaryRepository diaryRepository = new DiaryRepository();

	private void checkExist(final Long id) {
		if (diaryRepository.findById(id) == null) {
			throw new NullPointerException();
		}
	}

	void writeDiary(final String body) {
		Diary diary = new Diary(null, body.trim());

		diaryRepository.save(diary);
	}

	void deleteDiary(final Long id) {
		checkExist(id);

		Diary diary = new Diary(id, null);

		diaryRepository.delete(diary);
	}

	List<Diary> getDiaryList() {
		return diaryRepository.findAll();
	}

	void rewriteDiary(final Long id, final String body) {
		checkExist(id);

		Diary diary = new Diary(id, body.trim());

		diaryRepository.save(diary);
	}
}