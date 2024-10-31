package org.sopt.week3.dto;

import java.time.LocalDateTime;
import org.sopt.week3.constant.Category;
import org.sopt.week3.entity.DiaryEntity;

public record DiaryDTO(
        Long id,
        String title,
        String content,
        Category category,
        UserDTO userDTO,
        boolean isShare,
        LocalDateTime createdAt
) {
    public static DiaryDTO toDiaryDTO(final DiaryEntity diaryEntity) {
        return new DiaryDTO(
                diaryEntity.getId(),
                diaryEntity.getTitle(),
                diaryEntity.getContent(),
                diaryEntity.getCategory(),
                UserDTO.toUserDTO(diaryEntity.getUser()),
                diaryEntity.isShare(),
                diaryEntity.getCreatedAt()
        );
    }
}
