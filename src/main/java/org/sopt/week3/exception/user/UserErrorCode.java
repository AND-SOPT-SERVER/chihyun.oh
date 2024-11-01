package org.sopt.week3.exception.user;

import org.sopt.week3.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum UserErrorCode implements ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND),
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED);
    private final HttpStatus httpStatus;

    UserErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
