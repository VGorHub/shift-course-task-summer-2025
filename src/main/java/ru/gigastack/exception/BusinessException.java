package ru.gigastack.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final BusinessExceptionErrorCode errorCode;
    private final Exception exception;

    public BusinessException(BusinessExceptionErrorCode errorCode, String message, Exception e) {
        super(message);
        this.errorCode = errorCode;
        this.exception = e;
    }

}
