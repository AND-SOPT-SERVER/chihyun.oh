package org.sopt.week3.exception.diary;

public class DiaryException extends RuntimeException {
    private final DiaryErrorCode diaryErrorCode;

    public DiaryException(DiaryErrorCode diaryErrorCode) {
        this.diaryErrorCode = diaryErrorCode;
    }

    public DiaryException(String message, DiaryErrorCode diaryErrorCode) {
        super(message);
        this.diaryErrorCode = diaryErrorCode;
    }

    public DiaryException(String message, Throwable cause, DiaryErrorCode diaryErrorCode) {
        super(message, cause);
        this.diaryErrorCode = diaryErrorCode;
    }

    public DiaryException(Throwable cause, DiaryErrorCode diaryErrorCode) {
        super(cause);
        this.diaryErrorCode = diaryErrorCode;
    }

    public DiaryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                          DiaryErrorCode diaryErrorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.diaryErrorCode = diaryErrorCode;
    }

    public DiaryErrorCode getDiaryErrorCode() {
        return diaryErrorCode;
    }
}
