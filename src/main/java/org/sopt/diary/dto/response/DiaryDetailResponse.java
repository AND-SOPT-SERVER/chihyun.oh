package org.sopt.diary.dto.response;

import java.time.LocalDateTime;
import org.sopt.diary.dto.Diary;

public class DiaryDetailResponse {
    private final long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    private DiaryDetailResponse(
            final long id,
            final String title,
            final String content,
            final LocalDateTime createdAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static DiaryDetailResponse toDiaryDetailResponse(final Diary diary) {
        return new DiaryDetailResponse(
                diary.getId(),
                diary.getTitle(),
                diary.getContent(),
                diary.getCreatedAt()
        );
    }

    public long getId() {
        return id;
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
