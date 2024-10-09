package org.sopt.week1;

import java.util.List;

public class DiaryController {
	private Status status = Status.READY;
	private final DiaryService diaryService = new DiaryService();

	Status getStatus() {
		return status;
	}

	void boot() {
		this.status = Status.RUNNING;
	}

	void finish() {
		this.status = Status.FINISHED;
	}

	private void validateIdType(final String id) {
		try {
			Long parseTest = Long.parseLong(id.trim());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
	}

	private void validateBodyLength(final String body) {
		if (
			body.isBlank() ||
			body.trim().length() > 30
		) {
			throw new IllegalArgumentException();
		}
	}

	// APIS
	final List<Diary> getList() {
		return diaryService.getDiaryList();
	}

	final void post(final String body) {
		validateBodyLength(body);

		diaryService.writeDiary(body);
	}

	final void delete(final String id) {

	}

	final void patch(final String id, final String body) {

	}

	enum Status {
		READY,
		RUNNING,
		FINISHED,
		ERROR,
	}
}