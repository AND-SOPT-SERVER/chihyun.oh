package org.sopt.diary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    public List<DiaryEntity> findTop10ByOrderByCreatedAtDesc();
}
