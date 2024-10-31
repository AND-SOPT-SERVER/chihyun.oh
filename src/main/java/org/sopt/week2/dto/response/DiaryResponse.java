package org.sopt.week2.dto.response;

import org.sopt.week2.dto.Diary;

public record DiaryResponse(
        long id,
        String category,
        String title
) {
    public static DiaryResponse toDiaryResponse(final Diary diary) {
        return new DiaryResponse(
                diary.id(),
                diary.category(),
                diary.title()
        );
    }
}
