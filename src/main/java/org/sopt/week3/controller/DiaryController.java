package org.sopt.week3.controller;

import java.util.List;
import org.sopt.week3.dto.diary.DiaryDTO;
import org.sopt.week3.dto.diary.response.DiaryListResponse;
import org.sopt.week3.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/")
    ResponseEntity<DiaryListResponse> getDiaries(
            @RequestParam(required = false, defaultValue = "createdAt", value = "orderby") final String criteria,
            @RequestParam(required = false, defaultValue = "0") int page) {
        List<DiaryDTO> diaryDTOs = diaryService.getDiaries(criteria, page);
        DiaryListResponse diaryListResponse = DiaryListResponse.toDiaryListResponse(diaryDTOs);

        return ResponseEntity.ok(diaryListResponse);
    }
}
