package org.sopt.diary.dto.request;

public record DiaryRequest(
        Long id,
        String category,
        String name,
        String title,
        String content
) {
}