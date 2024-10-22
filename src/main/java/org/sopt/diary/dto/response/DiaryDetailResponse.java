package org.sopt.diary.dto.response;

import java.time.LocalDateTime;
import org.sopt.diary.dto.Diary;

public class DiaryDetailResponse {
    private final long id;
    private final String name;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    private DiaryDetailResponse(
            final long id,
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

    public static DiaryDetailResponse toDiaryDetailResponse(final Diary diary) {
        return new DiaryDetailResponse(
                diary.getId(),
                diary.getName(),
                diary.getTitle(),
                diary.getContent(),
                diary.getCreatedAt()
        );
    }

    public long getId() {
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
