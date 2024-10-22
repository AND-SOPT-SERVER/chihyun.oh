package org.sopt.diary.dto.request;

import java.time.LocalDateTime;

public class DiaryRequest {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    protected DiaryRequest(
            final Long id,
            final String title,
            final String content,
            final LocalDateTime createdAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
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