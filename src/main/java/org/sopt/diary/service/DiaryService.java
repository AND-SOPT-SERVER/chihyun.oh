package org.sopt.diary.service;

import java.util.List;
import java.util.Optional;
import org.sopt.diary.constant.ErrorMessage;
import org.sopt.diary.dto.Diary;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    private void validateExist(Optional<DiaryEntity> diaryEntity) {
        if (diaryEntity.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_EXIST_DIARY.getMessage());
        }
    }

    public void createDiary(final Diary diary) {
        diaryRepository.save(
                DiaryEntity.toCreateDiaryEntity(diary)
        );
    }

    public List<Diary> getDiaryList() {
        return diaryRepository.findTop10ByOrderByCreatedAtDesc().stream()
                .map(Diary::toDiaryDto)
                .toList();
    }

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
        diaryEntity.setTitle(diary.getTitle());
        diaryEntity.setContent(diary.getContent());

        diaryRepository.save(diaryEntity);
    }
}
