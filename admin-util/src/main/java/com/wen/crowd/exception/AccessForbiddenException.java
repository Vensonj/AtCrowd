package com.wen.crowd.exception;

/**
 * @author wen
 * @create 2021 1月 19 星期二 21:35
 * @description 未登录就访问受保护的资源抛出异常
 */
public class AccessForbiddenException extends RuntimeException {
	private static final Long SERIALIZATION_UID = 1L;

	public AccessForbiddenException() {
		super();
	}

	public AccessForbiddenException(String message) {
		super(message);
	}

	public AccessForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessForbiddenException(Throwable cause) {
		super(cause);
	}

	protected AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
