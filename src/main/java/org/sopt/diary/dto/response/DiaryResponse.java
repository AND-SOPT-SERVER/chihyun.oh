package org.sopt.diary.dto.response;

import org.sopt.diary.dto.Diary;

public class DiaryResponse {
    private final long id;
    private final String category;
    private final String title;

    private DiaryResponse(long id, String category, String title) {
        this.id = id;
        this.category = category;
        this.title = title;
    }

    public static DiaryResponse toDiaryResponse(final Diary diary) {
        return new DiaryResponse(
                diary.getId(),
                diary.getCategory(),
                diary.getTitle()
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
}
