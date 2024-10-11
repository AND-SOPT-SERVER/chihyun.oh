package org.sopt.week1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
	private final Map<Long, Diary> storage = new ConcurrentHashMap<>();
	private final AtomicLong numbering = new AtomicLong();

	void save(final Diary diary) {
		if (diary.getId() != null) {
			storage.put(diary.getId(), diary);
			return;
		}

		final Long id = numbering.addAndGet(1);
		storage.put(id, diary);
	}

	void delete(final Diary diary) {
		storage.remove(diary.getId());
	}

	Diary findById(final Long id) {
		return storage.get(id);
	}

	List<Diary> findAll() {
		return storage.values().stream().toList();
	}
}