package org.sopt.week3.dto.diary.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.sopt.week3.constant.DiaryConstant;

public record DiaryWriteRequest(
        @NotBlank(message = "제목은 필수 입력 값입니다.")
        @Size(max = DiaryConstant.MAX_TITLE_LENGTH, message = "제목은 10자 이내이어야 합니다.")
        String title,
        @NotBlank(message = "내용은 필수 입력 값입니다.")
        @Size(max = DiaryConstant.MAX_CONTENT_LENGTH, message = "내용은 30자 이내이어야 합니다.")
        String content,
        @NotBlank(message = "카테고리는 필수 입력 값입니다.")
        String category,
        @NotNull(message = "공유 여부는 필수 입력 값입니다.")
        Boolean isShare
) {
}
