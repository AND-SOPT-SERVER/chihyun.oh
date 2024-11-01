package org.sopt.week3.exception;

import org.sopt.week3.exception.diary.DiaryException;
import org.sopt.week3.exception.user.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DiaryException.class)
    protected ResponseEntity<ErrorResponseEntity> handleDiaryException(final DiaryException diaryException) {
        return ErrorResponseEntity.toResponseEntity(diaryException.getDiaryErrorCode());
    }

    @ExceptionHandler(UserException.class)
    protected ResponseEntity<ErrorResponseEntity> handleUserException(final UserException userException) {
        return ErrorResponseEntity.toResponseEntity(userException.getUserErrorCode());
    }
}
