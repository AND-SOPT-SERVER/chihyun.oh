package org.sopt.week1;

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

	// APIS
	final String getList() {
		return "get!";
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