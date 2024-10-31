package org.sopt.week3.dto.diary.response;

import java.util.List;
import org.sopt.week3.dto.diary.DiaryDTO;

public record MyDiariesResponse(
        List<MyDiaryResponse> myDiaryResponses
) {
    public static MyDiariesResponse toMyDiariesResponse(final List<DiaryDTO> diaryDTOs) {
        return new MyDiariesResponse(
                diaryDTOs.stream()
                        .map(MyDiaryResponse::toMyDiaryResponse)
                        .toList()
        );
    }
}
