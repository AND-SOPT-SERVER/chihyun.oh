package org.sopt.week2.api;

import java.util.List;
import org.sopt.week2.dto.Diary;
import org.sopt.week2.dto.request.DiaryListConditionRequest;
import org.sopt.week2.dto.request.DiaryRequest;
import org.sopt.week2.dto.response.DiaryDetailResponse;
import org.sopt.week2.dto.response.DiaryListResponse;
import org.sopt.week2.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/diary")
    ResponseEntity<String> create(@RequestBody DiaryRequest diaryRequest) {
        Diary diary = Diary.toDiaryDto(diaryRequest);
        diaryService.createDiary(diary);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/diary")
    ResponseEntity<DiaryListResponse> getList(DiaryListConditionRequest diaryListConditionRequest) {
        List<Diary> diaryList = diaryService.getDiaryList(diaryListConditionRequest);
        DiaryListResponse diaryListResponse = DiaryListResponse.toDiaryListResponse(diaryList);

        return ResponseEntity.ok(diaryListResponse);
    }

    @GetMapping("/diary/{id}")
    ResponseEntity<DiaryDetailResponse> getDetail(@PathVariable Long id) {
        Diary diary = diaryService.getDiaryDetail(id);
        DiaryDetailResponse diaryDetailResponse = DiaryDetailResponse.toDiaryDetailResponse(diary);

        return ResponseEntity.ok(diaryDetailResponse);
    }

    @PatchMapping("/diary/{id}")
    ResponseEntity<String> update(@PathVariable Long id, @RequestBody DiaryRequest diaryRequest) {
        Diary diary = Diary.toDiaryDto(diaryRequest);
        diaryService.update(id, diary);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/diary/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        diaryService.delete(id);

        return ResponseEntity.ok().build();
    }
}
