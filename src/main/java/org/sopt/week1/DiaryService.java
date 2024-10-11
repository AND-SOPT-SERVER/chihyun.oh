package org.sopt.week1;

import static org.sopt.week1.Main.UI.*;

import java.util.List;

public class DiaryService {
	private final DiaryRepository diaryRepository = new DiaryRepository();

	private void checkDeleted(Diary diary) {
		if (!diary.getIsDeleted()) {
			throw new InvalidInputException();
		}
	}

	private void checkNotDeleted(Diary diary) {
		if (diary.getIsDeleted()) {
			throw new InvalidInputException();
		}
	}

	void writeDiary(final String body) {
		Diary diary = new Diary(null, body.trim(), false);

		diaryRepository.save(diary);
	}

	void deleteDiary(final Long id) {
		diaryRepository.findById(id)
			.ifPresentOrElse(diary -> {
				checkNotDeleted(diary);
				diaryRepository.save(new Diary(id, diary.getBody(), true));
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
			.ifPresentOrElse(diary -> {
				checkNotDeleted(diary);
				diaryRepository.save(new Diary(id, body, false));
			},
			() -> {
				throw new InvalidInputException();
			}
		);
	}

	List<Diary> getRestoreDiaryList() {
		return diaryRepository.findAll().stream()
			.filter(Diary::getIsDeleted)
			.toList();
	}

	void restoreDiary(final Long id) {
		diaryRepository.findById(id)
			.ifPresentOrElse(diary -> {
				checkDeleted(diary);
				diaryRepository.save(new Diary(id, diary.getBody(), false));
			},
			() -> {
				throw new InvalidInputException();
			}
		);
	}

	void restoreDeleteDiary(final Long id) {
		diaryRepository.findById(id)
			.ifPresentOrElse(diary -> {
				checkDeleted(diary);
				diaryRepository.delete(diary);
			},
			() -> {
				throw new InvalidInputException();
			}
		);
	}
}