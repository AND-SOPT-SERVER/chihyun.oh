package org.sopt.week3.controller;

import java.util.List;
import org.sopt.week3.constant.DiarySortColumn;
import org.sopt.week3.dto.DiaryDTO;
import org.sopt.week3.dto.response.DiaryListResponse;
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
            @RequestParam(required = false, defaultValue = "createdAt", value = "orderby") final String criteria) {
        List<DiaryDTO> diaryDTOs = diaryService.getDiaries(DiarySortColumn.getSortColumnByCriteria(criteria));
        DiaryListResponse diaryListResponse = DiaryListResponse.toDiaryListResponse(diaryDTOs);

        return ResponseEntity.ok(diaryListResponse);
    }
}
