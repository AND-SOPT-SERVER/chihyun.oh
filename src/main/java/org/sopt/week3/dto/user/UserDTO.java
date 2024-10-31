package org.sopt.week3.dto.user;

import org.sopt.week3.dto.diary.request.MyDiariesRequest;
import org.sopt.week3.dto.user.request.UserLoginRequest;
import org.sopt.week3.dto.user.request.UserSignUpRequest;
import org.sopt.week3.entity.UserEntity;

public record UserDTO(
        Long id,
        String username,
        String password,
        String nickname
) {
    public static UserDTO toUserDTO(final UserEntity userEntity) {
        return new UserDTO(
                userEntity.getId(),
                userEntity.getUsername(),
                null,
                userEntity.getNickname()
        );
    }

    public static UserDTO toUserDTO(final UserSignUpRequest userSignUpRequest) {
        return new UserDTO(
                null,
                userSignUpRequest.username(),
                userSignUpRequest.password(),
                userSignUpRequest.nickname()
        );
    }

    public static UserDTO toUserDTO(final UserLoginRequest userLoginRequest) {
        return new UserDTO(
                null,
                null,
                userLoginRequest.password(),
                userLoginRequest.nickname()
        );
    }

    public static UserDTO toUserDTO(final MyDiariesRequest myDiariesRequest) {
        return new UserDTO(
                myDiariesRequest.id(),
                null,
                null,
                null
        );
    }
}
