package org.sopt.diary.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        // 에러 처리 많이 공부하기! (어렵다..) 일단 간단하게라도..
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}