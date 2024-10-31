package org.sopt.week3.service;

import java.util.List;
import org.sopt.week3.constant.DiarySortColumn;
import org.sopt.week3.dto.DiaryDTO;
import org.sopt.week3.entity.DiaryEntity;
import org.sopt.week3.repository.DiaryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @Transactional(readOnly = true)
    protected Sort getSortByCriteria(final DiarySortColumn criteria) {
        return Sort.by(
                Order.desc(criteria.getName())
        );
    }

    @Transactional(readOnly = true)
    public List<DiaryDTO> getDiaries(final DiarySortColumn criteria) {
        Sort sort = getSortByCriteria(criteria);
        List<DiaryEntity> diaryEntities = diaryRepository.findAllTop10ByOrderBy(sort);

        return diaryEntities.stream()
                .map(DiaryDTO::toDiaryDTO)
                .toList();
    }
}
