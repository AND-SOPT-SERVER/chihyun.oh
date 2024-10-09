package org.sopt.week1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
	private final Map<Long, String> storage = new ConcurrentHashMap<>();
	private final AtomicLong numbering = new AtomicLong();

	public void save(final Diary diary) {
		if (diary.getId() != null) {
			storage.put(diary.getId(), diary.getBody());
			return;
		}

		final Long id = numbering.getAndAdd(1);
		storage.put(id, diary.getBody());
	}
}