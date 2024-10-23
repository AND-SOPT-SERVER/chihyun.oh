package org.sopt.diary.dto.request;

public class DiaryRequest {
    private final Long id;
    private final String name;
    private final String title;
    private final String content;

    protected DiaryRequest(
            final Long id,
            final String name,
            final String title,
            final String content
    ) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
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
}