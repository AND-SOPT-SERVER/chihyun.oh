package org.sopt.diary.service;

import java.util.List;
import java.util.Optional;
import org.sopt.diary.constant.ErrorMessage;
import org.sopt.diary.dto.Diary;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
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
        Optional<Diary> diary = diaryRepository.findById(id).map(Diary::toDiaryDto);

        if (diary.isPresent()) {
            return diary.get();
        }

        throw new IllegalArgumentException(ErrorMessage.NOT_EXIST_DIARY.getMessage());
    }
}
