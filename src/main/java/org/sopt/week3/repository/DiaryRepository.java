package org.sopt.week3.repository;

import java.util.List;
import org.sopt.week3.entity.DiaryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    List<DiaryEntity> findAllTop10ByIsShareTrue(Pageable pageable);

    List<DiaryEntity> findAllTop10ByUserId(long userId, Pageable pageable);
}
