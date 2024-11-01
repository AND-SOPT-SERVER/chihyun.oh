package org.sopt.week3.service;

import java.util.List;
import org.sopt.week3.constant.DiarySortColumn;
import org.sopt.week3.dto.diary.DiaryDTO;
import org.sopt.week3.entity.DiaryEntity;
import org.sopt.week3.entity.UserEntity;
import org.sopt.week3.repository.DiaryRepository;
import org.sopt.week3.repository.UserRepository;
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
    private final UserRepository userRepository;

    public DiaryService(DiaryRepository diaryRepository, UserRepository userRepository) {
        this.diaryRepository = diaryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    protected void validateIsUserIdCorrect(final long userId, final long givenId) {
        if (userId != givenId) {
            // 에러 추가
            // 권한이 없습니다.
            throw new IllegalArgumentException();
        }
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
    public List<DiaryDTO> getMyDiaries(final long userId, final String criteria, final int page) {
        Sort sort = getSortByCriteria(DiarySortColumn.getSortColumnByCriteria(criteria));
        Pageable pageable = getPageableByPageAndSort(page, sort);

        List<DiaryEntity> diaryEntities = diaryRepository.findAllTop10ByUserId(userId, pageable);

        return diaryEntities.stream()
                .map(DiaryDTO::toDiaryDTO)
                .toList();
    }

    @Transactional
    public void writeDiary(final long userId, final DiaryDTO diaryDTO) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                // 에러 추가
                // USER NOT FOUND
                () -> new IllegalArgumentException()
        );

        diaryRepository.save(
                DiaryEntity.toDiaryEntity(diaryDTO, userEntity)
        );
    }

    @Transactional
    public void rewriteDiary(final long diaryId, final long userId, final DiaryDTO diaryDTO) {
        DiaryEntity diaryEntity = diaryRepository.findById(diaryId).orElseThrow(
                // 에러 추가
                // DIARY NOT FOUND
                () -> new IllegalArgumentException()
        );

        validateIsUserIdCorrect(diaryEntity.getUser().getId(), userId);

        diaryEntity.setTitle(diaryDTO.title());
        diaryEntity.setContent(diaryDTO.content());
        diaryEntity.setCategory(diaryDTO.category());
        diaryEntity.setShare(diaryDTO.isShare());
    }
}
