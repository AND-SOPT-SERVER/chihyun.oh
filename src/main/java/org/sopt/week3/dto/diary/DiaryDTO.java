package org.sopt.week3.dto.diary;

import java.time.LocalDateTime;
import org.sopt.week3.constant.Category;
import org.sopt.week3.dto.diary.request.DiaryWriteRequest;
import org.sopt.week3.dto.user.UserDTO;
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

    public static DiaryDTO toDiaryDTO(final long id, final DiaryWriteRequest diaryWriteRequest) {
        return new DiaryDTO(
                null,
                diaryWriteRequest.title(),
                diaryWriteRequest.content(),
                Category.getCategoryByName(diaryWriteRequest.category()),
                UserDTO.toUserDTO(id),
                diaryWriteRequest.isShare(),
                null
        );
    }
}
