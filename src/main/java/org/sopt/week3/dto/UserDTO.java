package org.sopt.week3.dto;

import org.sopt.week3.entity.UserEntity;

public record UserDTO(
        Long id,
        String username,
        String nickname
) {
    public static UserDTO toUserDTO(final UserEntity userEntity) {
        return new UserDTO(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getNickname()
        );
    }
}
