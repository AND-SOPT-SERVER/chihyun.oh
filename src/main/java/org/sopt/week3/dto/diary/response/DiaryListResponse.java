package org.sopt.week3.dto.diary.response;

import java.util.List;
import org.sopt.week3.dto.diary.DiaryDTO;

public record DiaryListResponse(
        List<DiaryResponse> diaryResponses
) {

    public static DiaryListResponse toDiaryListResponse(final List<DiaryDTO> diaryDTOS) {
        return new DiaryListResponse(
                diaryDTOS.stream()
                        .map(DiaryResponse::toDiaryResponse)
                        .toList()
        );
    }
}
