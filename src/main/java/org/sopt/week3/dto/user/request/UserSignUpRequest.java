package org.sopt.week3.dto.user.request;

public record UserSignUpRequest(
        String nickname,
        String username,
        String password
) {
}
