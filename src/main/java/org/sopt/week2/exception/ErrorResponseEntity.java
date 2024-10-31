package org.sopt.week2.exception;

import org.springframework.http.ResponseEntity;

public class ErrorResponseEntity {
    private ErrorResponseEntity() {
    }

    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(ErrorCode e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .build();
    }
}
