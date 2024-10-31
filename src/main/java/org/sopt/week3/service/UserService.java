package org.sopt.week3.service;

import org.sopt.week3.dto.user.UserDTO;
import org.sopt.week3.entity.UserEntity;
import org.sopt.week3.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void signUp(final UserDTO userDTO) {
        userRepository.save(
                UserEntity.toUserEntity(userDTO)
        );
    }
}
