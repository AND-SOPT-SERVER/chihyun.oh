package org.sopt.diary.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    TOO_MANY_PATCH_REQUEST(HttpStatus.TOO_MANY_REQUESTS, "조금 뒤에 다시 시도해주세요.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}