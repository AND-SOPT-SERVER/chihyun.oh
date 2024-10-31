package org.sopt.week3.dto.diary.response;

import static org.sopt.week3.constant.CommonPatterns.DATE_FORMAT;

import org.sopt.week3.dto.diary.DiaryDTO;

public record DiaryResponse(
        Long id,
        String title,
        String nickname,
        String createAt
) {
    public static DiaryResponse toDiaryResponse(final DiaryDTO diaryDTO) {
        return new DiaryResponse(
                diaryDTO.id(),
                diaryDTO.title(),
                diaryDTO.userDTO().nickname(),
                diaryDTO.createdAt().format(DATE_FORMAT)
        );
    }
}
