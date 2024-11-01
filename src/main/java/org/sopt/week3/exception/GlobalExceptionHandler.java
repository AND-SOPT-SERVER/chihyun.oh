package org.sopt.week3.exception;

import java.util.HashMap;
import java.util.Map;
import org.sopt.week3.exception.diary.DiaryException;
import org.sopt.week3.exception.user.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
