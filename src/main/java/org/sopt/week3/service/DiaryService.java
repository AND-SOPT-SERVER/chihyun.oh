package org.sopt.week3.service;

import java.util.List;
import org.sopt.week3.dto.DiaryDTO;
import org.sopt.week3.entity.DiaryEntity;
import org.sopt.week3.repository.DiaryRepository;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public List<DiaryDTO> getDiaries() {
        List<DiaryEntity> diaryEntities = diaryRepository.findAllTop10By();

        return diaryEntities.stream()
                .map(DiaryDTO::toDiaryDTO)
                .toList();
    }
}
