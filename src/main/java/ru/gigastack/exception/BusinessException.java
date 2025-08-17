package ru.gigastack.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.gigastack.enums.BusinessExceptionErrorCode;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {
    private final BusinessExceptionErrorCode errorCode;
    private final Exception exception;

    public BusinessException(BusinessExceptionErrorCode errorCode, String message, Exception e) {
        super(message);
        this.errorCode = errorCode;
        this.exception = e;
    }

}
