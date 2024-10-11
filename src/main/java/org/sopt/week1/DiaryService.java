package org.sopt.week1;

import static org.sopt.week1.Main.UI.*;

import java.util.List;

public class DiaryService {
	private final DiaryRepository diaryRepository = new DiaryRepository();
	private final DiaryRestoreRepository diaryRestoreRepository = new DiaryRestoreRepository();

	private void checkExistRecovery(final Long id) {
		if (diaryRestoreRepository.findById(id) == null) {
			throw new InvalidInputException();
		}
	}

	void writeDiary(final String body) {
		Diary diary = new Diary(null, body.trim());

		diaryRepository.save(diary);
	}

	void deleteDiary(final Long id) {
		diaryRepository.findById(id)
			.ifPresentOrElse(diary -> {
				diaryRepository.delete(diary);
				diaryRestoreRepository.save(diary);
			},
			() -> {
				throw new InvalidInputException();
			}
		);
	}

	List<Diary> getDiaryList() {
		return diaryRepository.findAll();
	}

	void rewriteDiary(final Long id, final String body) {
		diaryRepository.findById(id)
			.ifPresentOrElse(diary -> diaryRepository.save(new Diary(id, body)),
			() -> {
				throw new InvalidInputException();
			}
		);
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