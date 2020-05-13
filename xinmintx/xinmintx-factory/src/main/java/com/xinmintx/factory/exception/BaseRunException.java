package com.xinmintx.factory.exception;



import com.xinmintx.factory.exception.errorCodeStants.ErrorCodeConStants;
import org.springframework.http.HttpStatus;

/**
 * 异常Base类
 */
public class BaseRunException extends RuntimeException {
    private int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaseRunException() {
    }

    public BaseRunException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BaseRunException(String message, String status) {
        super(message);
        this.status = Integer.parseInt(status);
    }

    public BaseRunException(String message) {
        super(message);
    }

    public BaseRunException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRunException(Throwable cause) {
        super(cause);
    }

    public BaseRunException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseRunException(ErrorCodeConStants constants) {
        super(constants.getMsg());
        status = Integer.parseInt(constants.getCode());
    }
}
