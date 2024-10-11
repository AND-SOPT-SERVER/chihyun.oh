package org.sopt.week1;

import static org.sopt.week1.DiaryConstant.*;
import static org.sopt.week1.Main.UI.*;

import java.time.LocalDate;
import java.util.List;

public class DiaryService {
	private final DiaryRepository diaryRepository = new DiaryRepository();
	private int patchCount = PATCH_COUNT_DEFAULT.getValue();
	private LocalDate lastPatchDate = LocalDate.now();

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

	private void resetPatchCount() {
		lastPatchDate = LocalDate.now();
		patchCount = PATCH_COUNT_DEFAULT.getValue();
	}

	private void increasePatchCount() {
		++patchCount;
	}

	private void checkPatchCount() {
		if (lastPatchDate.isBefore(LocalDate.now())) {
			resetPatchCount();
		}

		if (patchCount == PATCH_COUNT_UPPER_LIMIT.getValue()) {
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
		checkPatchCount();

		diaryRepository.findById(id)
			.ifPresentOrElse(diary -> {
				checkNotDeleted(diary);
				diaryRepository.save(new Diary(id, body, false));
				increasePatchCount();
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