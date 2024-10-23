package org.sopt.diary.exception;

import org.springframework.http.ResponseEntity;

public class ErrorResponseEntity {
    private final int status;
    private final String name;
    private final String message;

    private ErrorResponseEntity(int status, String name, String message) {
        this.status = status;
        this.name = name;
        this.message = message;
    }

    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(ErrorCode e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(
                        new ErrorResponseEntity(
                                e.getHttpStatus().value(),
                                e.name(),
                                e.getMessage()
                        )
                );
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
