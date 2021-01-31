package com.wen.crowd.exception;

/**
 * @author wen
 * @create 2021 1月 17 星期日 22:14
 * @description 登录失败后抛出的异常
 */
public class LoginFailedException extends RuntimeException {
    private static final Long SERIALIZATION_UID = 1L;

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
