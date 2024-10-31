package org.sopt.week3.repository;

import org.sopt.week3.entity.SoptMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoptMemberRepository extends JpaRepository<SoptMemberEntity, Long> {
}
