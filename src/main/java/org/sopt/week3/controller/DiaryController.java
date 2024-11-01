package org.sopt.week3.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;
import org.sopt.week3.dto.diary.DiaryDTO;
import org.sopt.week3.dto.diary.request.DiaryRewriteRequest;
import org.sopt.week3.dto.diary.request.DiaryWriteRequest;
import org.sopt.week3.dto.diary.response.DiariesResponse;
import org.sopt.week3.dto.diary.response.MyDiariesResponse;
import org.sopt.week3.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary")
@Validated
public class DiaryController {
    private static final String USER_ID_HEADER_NAME = "User-Id";

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping
    ResponseEntity<DiariesResponse> getDiaries(
            @RequestParam(required = false, defaultValue = "createdAt", value = "orderby") final String criteria,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero int page) {
        List<DiaryDTO> diaryDTOs = diaryService.getDiaries(criteria, page);
        DiariesResponse diariesResponse = DiariesResponse.toDiaryListResponse(diaryDTOs);

        return ResponseEntity.ok(diariesResponse);
    }

    @GetMapping("/me")
    ResponseEntity<MyDiariesResponse> getMyDiaries(
            @RequestParam(required = false, defaultValue = "createdAt", value = "orderby") final String criteria,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero int page,
            @RequestHeader(name = USER_ID_HEADER_NAME) @NotNull @Positive long userId
    ) {
        List<DiaryDTO> diaryDTOs = diaryService.getMyDiaries(userId, criteria, page);
        MyDiariesResponse myDiariesResponse = MyDiariesResponse.toMyDiariesResponse(diaryDTOs);

        return ResponseEntity.ok(myDiariesResponse);
    }

    @PostMapping
    ResponseEntity<String> writeDiary(
            @RequestBody @Valid DiaryWriteRequest diaryWriteRequest,
            @RequestHeader(name = USER_ID_HEADER_NAME) @NotNull @Positive long userId
    ) {
        diaryService.writeDiary(userId, DiaryDTO.toDiaryDTO(diaryWriteRequest));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<String> rewriteDiary(
            @PathVariable(value = "id") @Positive long diaryId,
            @RequestBody @Valid DiaryRewriteRequest diaryRewriteRequest,
            @RequestHeader(name = USER_ID_HEADER_NAME) @NotNull @Positive long userId
    ) {
        diaryService.rewriteDiary(diaryId, userId, DiaryDTO.toDiaryDTO(diaryRewriteRequest));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteDiary(
            @PathVariable(value = "id") @Positive long diaryId,
            @RequestHeader(name = USER_ID_HEADER_NAME) @NotNull @Positive long userId
    ) {
        diaryService.deleteDiary(diaryId, userId);

        return ResponseEntity.ok().build();
    }
}
