package com.xinmintx.factory.exception;

import com.xinmintx.factory.exception.errorCodeStants.ErrorCodeConStants;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/19
 * @time: 17:53
 * @Description:
 */
public class BaseIoException extends IOException {
    private int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public BaseIoException() {
    }

    public BaseIoException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BaseIoException(String message, String status) {
        super(message);
        this.status = Integer.parseInt(status);
    }

    public BaseIoException(String message) {
        super(message);
    }

    public BaseIoException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseIoException(Throwable cause) {
        super(cause);
    }

    public BaseIoException(ErrorCodeConStants constants) {
        super(constants.getMsg());
        status = Integer.parseInt(constants.getCode());
    }
    
}
