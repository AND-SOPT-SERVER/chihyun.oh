package org.sopt.week3.repository;

import java.util.List;
import org.sopt.week3.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    List<DiaryEntity> findAllTop10By();
}
