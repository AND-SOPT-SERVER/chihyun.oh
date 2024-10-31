package org.sopt.week2.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    TOO_MANY_PATCH_REQUEST(HttpStatus.TOO_MANY_REQUESTS),
    DIARY_NOT_EXIST(HttpStatus.BAD_REQUEST),
    CONTENT_LENGTH_OVER(HttpStatus.BAD_REQUEST),
    TITLE_DUPLICATED(HttpStatus.BAD_REQUEST);

    private final HttpStatus httpStatus;

    ErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
