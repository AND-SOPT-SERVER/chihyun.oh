package org.sopt.week3.service;

import java.util.List;
import org.sopt.week3.constant.DiarySortColumn;
import org.sopt.week3.dto.diary.DiaryDTO;
import org.sopt.week3.entity.DiaryEntity;
import org.sopt.week3.repository.DiaryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiaryService {
    private static final int PAGE_SIZE = 10;

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
    protected Pageable getPageableByPageAndSort(final int page, final Sort sort) {
        return PageRequest.of(
                page,
                PAGE_SIZE,
                sort
        );
    }

    @Transactional(readOnly = true)
    public List<DiaryDTO> getDiaries(final String criteria, final int page) {
        Sort sort = getSortByCriteria(DiarySortColumn.getSortColumnByCriteria(criteria));
        Pageable pageable = getPageableByPageAndSort(page, sort);

        List<DiaryEntity> diaryEntities = diaryRepository.findAllTop10ByIsShareTrue(pageable);

        return diaryEntities.stream()
                .map(DiaryDTO::toDiaryDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DiaryDTO> getMyDiaries(final long id, final String criteria, final int page) {
        Sort sort = getSortByCriteria(DiarySortColumn.getSortColumnByCriteria(criteria));
        Pageable pageable = getPageableByPageAndSort(page, sort);

        List<DiaryEntity> diaryEntities = diaryRepository.findAllTop10ByUserId(id, pageable);

        return diaryEntities.stream()
                .map(DiaryDTO::toDiaryDTO)
                .toList();
    }

    @Transactional
    public void writeDiary(final DiaryDTO diaryDTO) {
        diaryRepository.save(
                DiaryEntity.toDiaryEntity(diaryDTO)
        );
    }
}
