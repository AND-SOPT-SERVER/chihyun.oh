package org.sopt.week3.dto.diary.response;

import java.util.List;
import org.sopt.week3.dto.diary.DiaryDTO;

public record DiariesResponse(
        List<DiaryResponse> diaryResponses
) {

    public static DiariesResponse toDiaryListResponse(final List<DiaryDTO> diaryDTOS) {
        return new DiariesResponse(
                diaryDTOS.stream()
                        .map(DiaryResponse::toDiaryResponse)
                        .toList()
        );
    }
}
