package com.xinmintx.hstx.exception;


import com.xinmintx.hstx.exception.errorCodeStants.ErrorCodeConStants;
import org.springframework.http.HttpStatus;

/**
 * 异常Base类
 */
public class BaseException extends RuntimeException {
    private int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaseException() {
    }

    public BaseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BaseException(String message, String status) {
        super(message);
        this.status = Integer.parseInt(status);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseException(ErrorCodeConStants constants) {
        super(constants.getMsg());
        status = Integer.parseInt(constants.getCode());
    }
}
