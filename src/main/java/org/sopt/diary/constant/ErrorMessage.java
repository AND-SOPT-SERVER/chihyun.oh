package org.sopt.diary.constant;

public enum ErrorMessage {
    CONTENT_LENGTH_OVER("일기 내용은 30자까지 가능해요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
