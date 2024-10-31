package org.sopt.week2.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long>, JpaSpecificationExecutor<DiaryEntity> {
    List<DiaryEntity> findTop10By(Sort sort);

    List<DiaryEntity> findTop10ByCategory(String category, Sort sort);

    Optional<DiaryEntity> findByTitle(String title);
}