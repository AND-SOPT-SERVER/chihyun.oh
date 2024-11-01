package org.sopt.week3.dto.user.request;

import jakarta.validation.constraints.NotBlank;

public record UserSignUpRequest(
        @NotBlank(message = "닉네임은 필수 입력 값입니다.")
        String nickname,
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        String password,
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        String username
) {
}
