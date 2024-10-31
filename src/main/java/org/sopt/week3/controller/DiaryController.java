package org.sopt.week3.controller;

import java.util.List;
import org.sopt.week3.dto.diary.DiaryDTO;
import org.sopt.week3.dto.diary.request.MyDiariesRequest;
import org.sopt.week3.dto.diary.response.DiariesResponse;
import org.sopt.week3.dto.diary.response.MyDiariesResponse;
import org.sopt.week3.dto.user.UserDTO;
import org.sopt.week3.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            @RequestBody MyDiariesRequest myDiariesRequest
    ) {
        List<DiaryDTO> diaryDTOs = diaryService.getMyDiaries(UserDTO.toUserDTO(myDiariesRequest), criteria, page);
        MyDiariesResponse myDiariesResponse = MyDiariesResponse.toMyDiariesResponse(diaryDTOs);
        
        return ResponseEntity.ok(myDiariesResponse);
    }
}
