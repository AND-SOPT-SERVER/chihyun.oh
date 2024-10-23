package org.sopt.diary.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    List<DiaryEntity> findTop10ByOrderByCreatedAtDesc();

    List<DiaryEntity> findTop10ByCategoryOrderByCreatedAtDesc(String category);

    Optional<DiaryEntity> findByTitle(String title);
}