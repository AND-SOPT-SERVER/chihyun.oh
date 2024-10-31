package org.sopt.week3.dto.response;

import java.time.LocalDateTime;
import org.sopt.week3.dto.DiaryDTO;

public record DiaryResponse(
        Long id,
        String title,
        String nickname,
        LocalDateTime createAt
) {

    public static DiaryResponse toDiaryResponse(final DiaryDTO diaryDTO) {
        return new DiaryResponse(
                diaryDTO.id(),
                diaryDTO.title(),
                diaryDTO.userDTO().nickname(),
                null // TODO: 일기 엔티티에 작성 시간 넣기
        );
    }
}
