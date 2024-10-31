package org.sopt.week2.dto;

import java.time.LocalDateTime;
import org.sopt.week2.dto.request.DiaryRequest;
import org.sopt.week2.repository.DiaryEntity;

public record Diary(
        Long id,
        String category,
        String name,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static Diary toDiaryDto(final DiaryRequest diaryRequest) {
        return new Diary(
                diaryRequest.id(),
                diaryRequest.category(),
                diaryRequest.name(),
                diaryRequest.title(),
                diaryRequest.content(),
                null,
                null
        );
    }

    public static Diary toDiaryDto(final DiaryEntity diaryEntity) {
        return new Diary(
                diaryEntity.getId(),
                diaryEntity.getCategory(),
                diaryEntity.getName(),
                diaryEntity.getTitle(),
                diaryEntity.getContent(),
                diaryEntity.getCreatedAt(),
                diaryEntity.getUpdatedAt()
        );
    }
}
