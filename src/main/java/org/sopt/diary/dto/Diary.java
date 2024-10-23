package org.sopt.diary.dto;

import java.time.LocalDateTime;
import org.sopt.diary.dto.request.DiaryRequest;
import org.sopt.diary.repository.DiaryEntity;

public class Diary {
    private final Long id;
    private final String name;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Diary(
            final Long id,
            final String name,
            final String title,
            final String content,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Diary toDiaryDto(final DiaryRequest diaryRequest) {
        return new Diary(
                diaryRequest.getId(),
                diaryRequest.getName(),
                diaryRequest.getTitle(),
                diaryRequest.getContent(),
                null,
                null
        );
    }

    public static Diary toDiaryDto(final DiaryEntity diaryEntity) {
        return new Diary(
                diaryEntity.getId(),
                diaryEntity.getName(),
                diaryEntity.getTitle(),
                diaryEntity.getContent(),
                diaryEntity.getCreatedAt(),
                diaryEntity.getUpdatedAt()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
