package org.sopt.week2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.sopt.week2.dto.Diary;
import org.sopt.week2.dto.request.DiaryListConditionRequest;
import org.sopt.week2.exception.CustomException;
import org.sopt.week2.exception.ErrorCode;
import org.sopt.week2.repository.DiaryEntity;
import org.sopt.week2.repository.DiaryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DiaryService {
    private static final int PATCH_TIME_LIMIT = 5;
    private static final int START_PAGE = 0;
    private static final int SEARCH_DIARY_LIST_SIZE = 10;
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    private void validateExist(Optional<DiaryEntity> diaryEntity) {
        if (diaryEntity.isEmpty()) {
            throw new CustomException(ErrorCode.DIARY_NOT_EXIST);
        }
    }

    private void validateOverEnablePatchTime(DiaryEntity diaryEntity) {
        LocalDateTime enablePatchTime =
                LocalDateTime.from(
                        diaryEntity.getUpdatedAt()
                ).plusMinutes(PATCH_TIME_LIMIT);

        if (diaryEntity.getCreatedAt().isBefore(diaryEntity.getUpdatedAt()) &&
                LocalDateTime.now().isBefore(enablePatchTime)) {
            throw new CustomException(ErrorCode.TOO_MANY_PATCH_REQUEST);
        }
    }

    private void validateNotExistTitle(String title) {
        diaryRepository.findByTitle(title)
                .ifPresent(diary -> {
                    throw new CustomException(ErrorCode.TITLE_DUPLICATED);
                });
    }

    private Sort getSortDiaryList() {
        return Sort.by(
                Order.desc("createdAt"),
                Order.desc("contentLength")
        );
    }

    private Pageable getPageableDiaryList() {
        return PageRequest.of(START_PAGE, SEARCH_DIARY_LIST_SIZE, getSortDiaryList());
    }

    @Transactional
    public void createDiary(final Diary diary) {
        validateNotExistTitle(diary.title());

        diaryRepository.save(
                DiaryEntity.toCreateDiaryEntity(diary)
        );
    }

    @Transactional(readOnly = true)
    public List<Diary> getDiaryList(DiaryListConditionRequest diaryListConditionRequest) {
        return diaryRepository.findAll(
                        DiaryListSpecification.searchDiaryList(diaryListConditionRequest.getConditions()),
                        getPageableDiaryList()
                )
                .stream()
                .map(Diary::toDiaryDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Diary getDiaryDetail(final Long id) {
        Optional<DiaryEntity> foundDiaryEntity = diaryRepository.findById(id);

        validateExist(foundDiaryEntity);

        return foundDiaryEntity.map(Diary::toDiaryDto).get();
    }

    @Transactional
    public void update(final Long id, final Diary diary) {
        Optional<DiaryEntity> foundDiaryEntity = diaryRepository.findById(id);
        validateExist(foundDiaryEntity);

        DiaryEntity diaryEntity = foundDiaryEntity.get();
        validateOverEnablePatchTime(diaryEntity);

        validateNotExistTitle(diary.title());
        diaryEntity.setTitle(diary.title());
        diaryEntity.setContent(diary.content());

        diaryRepository.save(diaryEntity);
    }

    @Transactional
    public void delete(Long id) {
        validateExist(diaryRepository.findById(id));

        diaryRepository.deleteById(id);
    }
}
