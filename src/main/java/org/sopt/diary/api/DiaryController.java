package org.sopt.diary.api;

import java.util.ArrayList;
import java.util.List;

import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiaryController {
	private final DiaryService diaryService;

	public DiaryController(DiaryService diaryService) {
		this.diaryService = diaryService;
	}

	@PostMapping("/post")
	void post() {
		diaryService.createDiary();
	}

	@GetMapping("/post")
	ResponseEntity<DiaryListResponse> get() {
		// (1) Service 로 부터 가져온 DiaryList
		List<Diary> diaryList = diaryService.getList();

		// (2) Client 와 협의한 interface 로 변환
		List<DiaryResponse> diaryResponseList = new ArrayList<>();
		for (Diary diary : diaryList) {
			diaryResponseList.add(new DiaryResponse(diary.getId(), diary.getTitle(), diary.getContent()));
		}
		return ResponseEntity.ok(new DiaryListResponse(diaryResponseList));
	}
}
