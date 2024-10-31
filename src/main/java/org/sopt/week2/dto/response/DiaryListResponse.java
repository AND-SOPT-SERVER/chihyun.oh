package org.sopt.week2.dto.response;

import java.util.List;
import org.sopt.week2.dto.Diary;

public record DiaryListResponse(
        List<DiaryResponse> diaryList
) {
    public static DiaryListResponse toDiaryListResponse(List<Diary> diaries) {
        return new DiaryListResponse(
                diaries.stream()
                        .map(DiaryResponse::toDiaryResponse)
                        .toList()
        );
    }
}
