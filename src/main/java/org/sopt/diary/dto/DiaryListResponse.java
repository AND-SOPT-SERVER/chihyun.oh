package org.sopt.diary.dto;

import java.util.List;

public class DiaryListResponse {
	private final List<DiaryResponse> diaryList;

	private DiaryListResponse(List<DiaryResponse> diaryList) {
		this.diaryList = diaryList;
	}

	public static DiaryListResponse toDiaryListResponse(List<Diary> diaries) {
		return new DiaryListResponse(
				diaries.stream()
						.map(DiaryResponse::toDiaryResponse)
						.toList()
		);
	}

	public List<DiaryResponse> getDiaryList() {
		return diaryList;
	}
}
