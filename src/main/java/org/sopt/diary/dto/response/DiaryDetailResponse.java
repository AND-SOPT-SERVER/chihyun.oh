package org.sopt.diary.dto.response;

import java.time.LocalDateTime;
import org.sopt.diary.dto.Diary;

public record DiaryDetailResponse(
        long id,
        String name,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static DiaryDetailResponse toDiaryDetailResponse(final Diary diary) {
        return new DiaryDetailResponse(
                diary.id(),
                diary.name(),
                diary.title(),
                diary.content(),
                diary.createdAt(),
                diary.updatedAt()
        );
    }
}
