package org.sopt.diary.dto.response;

import org.sopt.diary.dto.Diary;

public class DiaryResponse {
    private final long id;
    private final String category;
    private final String title;
    private final String content;

    private DiaryResponse(long id, String category, String title, String content) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.content = content;
    }

    public static DiaryResponse toDiaryResponse(final Diary diary) {
        return new DiaryResponse(
                diary.getId(),
                diary.getCategory(),
                diary.getTitle(),
                diary.getContent()
        );
    }

    public long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
