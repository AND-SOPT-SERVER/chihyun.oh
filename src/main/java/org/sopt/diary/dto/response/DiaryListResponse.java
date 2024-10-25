package org.sopt.diary.dto.response;

import java.util.List;
import org.sopt.diary.dto.Diary;

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
