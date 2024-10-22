package org.sopt.diary.dto.response;

import org.sopt.diary.dto.Diary;

public class DiaryResponse {
    private final long id;
    private final String title;

    private DiaryResponse(final long id, final String title) {
        this.id = id;
        this.title = title;
    }

    public static DiaryResponse toDiaryResponse(final Diary diary) {
        return new DiaryResponse(
                diary.getId(),
                diary.getTitle()
        );
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
