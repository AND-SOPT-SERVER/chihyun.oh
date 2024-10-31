package org.sopt.week3.dto.user.request;

public record UserLoginRequest(
        String nickname,
        String password
) {
}
