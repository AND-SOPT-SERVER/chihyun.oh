package org.sopt.week3.dto.user.response;

import org.sopt.week3.dto.user.UserDTO;

public record UserLoginResponse(
        long id
) {
    public static UserLoginResponse toUserLoginResponse(final UserDTO userDTO) {
        return new UserLoginResponse(
                userDTO.id()
        );
    }
}
