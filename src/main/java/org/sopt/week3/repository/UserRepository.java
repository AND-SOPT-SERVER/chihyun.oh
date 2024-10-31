package org.sopt.week3.repository;

import java.util.Optional;
import org.sopt.week3.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findOneByNickname(String nickname);
}
