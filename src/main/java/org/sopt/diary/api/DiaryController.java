package org.sopt.diary.api;

import java.util.ArrayList;
import java.util.List;

import org.sopt.diary.constant.ResponseMessage;
import org.sopt.diary.service.DiaryDto;
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
	ResponseEntity<String> createDiary(@RequestBody DiaryRequestDto diaryRequestDto) {
		DiaryDto diaryDto = DiaryDto.toDiaryDto(diaryRequestDto);
		diaryService.createDiary(diaryDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.DIARY_CREATED);
	}

	@GetMapping("/post")
	ResponseEntity<DiaryListResponse> get() {
		// (1) Service 로 부터 가져온 DiaryList
		List<DiaryDto> diaryDtoList = diaryService.getList();

		// (2) Client 와 협의한 interface 로 변환
		List<DiaryResponse> diaryResponseList = new ArrayList<>();
		for (DiaryDto diaryDto : diaryDtoList) {
			diaryResponseList.add(new DiaryResponse(diaryDto.getId(), diaryDto.getTitle(), diaryDto.getContent()));
		}
		return ResponseEntity.ok(new DiaryListResponse(diaryResponseList));
	}
}
