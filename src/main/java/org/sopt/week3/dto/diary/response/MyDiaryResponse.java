package org.sopt.week3.dto.diary.response;

import static org.sopt.week3.constant.CommonPatterns.DATE_FORMAT;

import org.sopt.week3.dto.diary.DiaryDTO;

public record MyDiaryResponse(
        long id,
        String title,
        String createdAt
) {
    public static MyDiaryResponse toMyDiaryResponse(final DiaryDTO diaryDTO) {
        return new MyDiaryResponse(
                diaryDTO.id(),
                diaryDTO.title(),
                diaryDTO.createdAt().format(DATE_FORMAT)
        );
    }
}
