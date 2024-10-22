package org.sopt.diary.api;

import java.util.List;

import org.sopt.diary.constant.ResponseMessage;
import org.sopt.diary.dto.DiaryListResponse;
import org.sopt.diary.dto.DiaryRequest;
import org.sopt.diary.dto.DiaryResponse;
import org.sopt.diary.dto.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	ResponseEntity<String> createDiary(@RequestBody DiaryRequest diaryRequest) {
		Diary diary = Diary.toDiaryDto(diaryRequest);
		diaryService.createDiary(diary);

		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.DIARY_CREATED);
	}

	@GetMapping("/diary")
	ResponseEntity<DiaryListResponse> getList() {
		List<Diary> diaryList = diaryService.getList();
		DiaryListResponse diaryListResponse = DiaryListResponse.toDiaryListResponse(diaryList);

		return ResponseEntity.ok(diaryListResponse);
	}
}
