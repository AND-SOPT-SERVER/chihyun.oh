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

    private Diary(
            final Long id,
            final String name,
            final String title,
            final String content,
            final LocalDateTime createdAt
    ) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static Diary toDiaryDto(final DiaryRequest diaryRequest) {
        return new Diary(
                diaryRequest.getId(),
                diaryRequest.getName(),
                diaryRequest.getTitle(),
                diaryRequest.getContent(),
                diaryRequest.getCreatedAt());
    }

    public static Diary toDiaryDto(final DiaryEntity diaryEntity) {
        return new Diary(
                diaryEntity.getId(),
                diaryEntity.getName(),
                diaryEntity.getTitle(),
                diaryEntity.getContent(),
                diaryEntity.getCreatedAt());
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
}
