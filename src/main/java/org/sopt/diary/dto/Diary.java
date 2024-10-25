package org.sopt.diary.dto;

import java.time.LocalDateTime;
import org.sopt.diary.dto.request.DiaryRequest;
import org.sopt.diary.repository.DiaryEntity;

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
