package org.sopt.week3.exception.diary;

import org.sopt.week3.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum DiaryErrorCode implements ErrorCode {
    NO_AUTHORIZATION_WITH_LOGIN(HttpStatus.FORBIDDEN),
    NOT_FOUND(HttpStatus.NOT_FOUND);
    private final HttpStatus httpStatus;

    DiaryErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
