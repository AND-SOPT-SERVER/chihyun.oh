package org.sopt.diary.constant;

public enum ErrorMessage {
    CONTENT_LENGTH_OVER("일기 내용은 30자까지 가능해요."),
    NOT_EXIST_DIARY("존재하지 않는 일기에요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
