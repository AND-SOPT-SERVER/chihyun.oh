package org.sopt.week1;

import java.util.List;

public class DiaryService {
	private final DiaryRepository diaryRepository = new DiaryRepository();
	private final DiaryRestoreRepository diaryRestoreRepository = new DiaryRestoreRepository();

	private void checkExist(final Long id) {
		if (diaryRepository.findById(id) == null) {
			throw new NullPointerException();
		}
	}

	private void checkExistRecovery(final Long id) {
		if (diaryRestoreRepository.findById(id) == null) {
			throw new NullPointerException();
		}
	}

	void writeDiary(final String body) {
		Diary diary = new Diary(null, body.trim());

		diaryRepository.save(diary);
	}

	void deleteDiary(final Long id) {
		checkExist(id);

		Diary diary = diaryRepository.findById(id);

		diaryRepository.delete(diary);
		diaryRestoreRepository.save(diary);
	}

	List<Diary> getDiaryList() {
		return diaryRepository.findAll();
	}

	void rewriteDiary(final Long id, final String body) {
		checkExist(id);

		Diary diary = new Diary(id, body.trim());

		diaryRepository.save(diary);
	}

	List<Diary> getRestoreDiaryList() {
		return diaryRestoreRepository.findAll();
	}

	void restoreDiary(final Long id) {
		checkExistRecovery(id);

		Diary diary = diaryRestoreRepository.findById(id);

		diaryRestoreRepository.delete(diary);
		diaryRepository.save(diary);
	}

	void restoreDeleteDiary(final Long id) {
		checkExistRecovery(id);

		Diary diary = diaryRestoreRepository.findById(id);

		diaryRestoreRepository.delete(diary);
	}
}