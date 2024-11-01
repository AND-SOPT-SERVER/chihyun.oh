package org.sopt.week3.service;

import org.sopt.week3.dto.user.UserDTO;
import org.sopt.week3.entity.UserEntity;
import org.sopt.week3.exception.user.UserErrorCode;
import org.sopt.week3.exception.user.UserException;
import org.sopt.week3.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    protected void validateIsPasswordCorrect(final UserEntity userEntity, final String password) {
        if (!userEntity.isPasswordCorrect(password)) {
            throw new UserException(UserErrorCode.PASSWORD_MISMATCH);
        }
    }

    @Transactional
    public void signUp(final UserDTO userDTO) {
        userRepository.save(
                UserEntity.toUserEntity(userDTO)
        );
    }

    @Transactional(readOnly = true)
    public UserDTO login(final UserDTO userDTO) {
        UserEntity userEntity = userRepository.findOneByNickname(userDTO.nickname()).orElseThrow(
                () -> new UserException(UserErrorCode.NOT_FOUND)
        );

        validateIsPasswordCorrect(userEntity, userDTO.password());

        return UserDTO.toUserDTO(userEntity);
    }
}
