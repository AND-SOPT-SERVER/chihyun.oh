package org.sopt.week3.dto.diary.request;

public record DiaryWriteRequest(
        String title,
        String content,
        String category,
        boolean isShare
) {
}
