package org.sopt.week3.exception.user;

public class UserException extends RuntimeException {
    private final UserErrorCode userErrorCode;

    public UserException(UserErrorCode userErrorCode) {
        this.userErrorCode = userErrorCode;
    }

    public UserException(String message, UserErrorCode userErrorCode) {
        super(message);
        this.userErrorCode = userErrorCode;
    }

    public UserException(String message, Throwable cause, UserErrorCode userErrorCode) {
        super(message, cause);
        this.userErrorCode = userErrorCode;
    }

    public UserException(Throwable cause, UserErrorCode userErrorCode) {
        super(cause);
        this.userErrorCode = userErrorCode;
    }

    public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                         UserErrorCode userErrorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.userErrorCode = userErrorCode;
    }

    public UserErrorCode getUserErrorCode() {
        return userErrorCode;
    }
}
