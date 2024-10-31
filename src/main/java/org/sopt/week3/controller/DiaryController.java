package org.sopt.week3.controller;

import java.util.List;
import org.sopt.week3.dto.diary.DiaryDTO;
import org.sopt.week3.dto.diary.request.DiaryWriteRequest;
import org.sopt.week3.dto.diary.response.DiariesResponse;
import org.sopt.week3.dto.diary.response.MyDiariesResponse;
import org.sopt.week3.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    ResponseEntity<DiariesResponse> getDiaries(
            @RequestParam(required = false, defaultValue = "createdAt", value = "orderby") final String criteria,
            @RequestParam(required = false, defaultValue = "0") int page) {
        List<DiaryDTO> diaryDTOs = diaryService.getDiaries(criteria, page);
        DiariesResponse diariesResponse = DiariesResponse.toDiaryListResponse(diaryDTOs);

        return ResponseEntity.ok(diariesResponse);
    }

    @GetMapping("/me")
    ResponseEntity<MyDiariesResponse> getMyDiaries(
            @RequestParam(required = false, defaultValue = "createdAt", value = "orderby") final String criteria,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestHeader long id
    ) {
        // 헤더로 유저 아이디 검사 추가
        List<DiaryDTO> diaryDTOs = diaryService.getMyDiaries(id, criteria, page);
        MyDiariesResponse myDiariesResponse = MyDiariesResponse.toMyDiariesResponse(diaryDTOs);

        return ResponseEntity.ok(myDiariesResponse);
    }

    @PostMapping("/")
    ResponseEntity<String> writeDiary(
            @RequestBody DiaryWriteRequest diaryWriteRequest,
            @RequestHeader long id
    ) {
        diaryService.writeDiary(DiaryDTO.toDiaryDTO(id, diaryWriteRequest));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
