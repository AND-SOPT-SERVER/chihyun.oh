package org.sopt.diary.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    public List<DiaryEntity> findTop10ByOrderByCreatedAtDesc();

    public List<DiaryEntity> findTop10ByCategoryOrderByCreatedAtDesc(String category);

    public Optional<DiaryEntity> findByTitle(String title);
}