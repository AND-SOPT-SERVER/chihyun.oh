package org.sopt.week1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRecoveryRepository {
	private final Map<Long, String> storage = new ConcurrentHashMap<>();

	void save(final Diary diary) {
		storage.put(diary.getId(), diary.getBody());
	}

	void delete(final Diary diary) {
		storage.remove(diary.getId());
	}

	Diary findById(final Long id) {
		final String body = storage.get(id);

		if (body == null) {
			return null;
		}

		return new Diary(id, body);
	}

	List<Diary> findAll() {
		return storage.entrySet().stream()
			.map(entry -> new Diary(entry.getKey(), entry.getValue()))
			.toList();
	}
}