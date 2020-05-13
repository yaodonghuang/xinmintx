package com.xinmintx.merchant.exception;

import com.xinmintx.merchant.common.ErrorCodeConstants;
import com.xinmintx.merchant.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * 全局异常捕获
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 500- server error
     */
    @ExceptionHandler(Exception.class)
    public ResultCode ExceptionHandler(HttpServletResponse response, Exception ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResultCode(Integer.valueOf(ErrorCodeConstants.EX_OTHER_CODE.getCode()), ex.getMessage());
    }

    /**
     * 500- server error
     */
    @ExceptionHandler(BaseException.class)
    public ResultCode BaseExceptionHandler(HttpServletResponse response, BaseException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.OK.value());
        return new ResultCode(Integer.valueOf(ex.getStatus()), ex.getMessage());
    }

    /**
     * 缺少请求参数- Bad Request
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultCode handleMissingServletRequestParameterException(HttpServletResponse response, MissingServletRequestParameterException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultCode(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 参数解析失败- Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultCode handleHttpMessageNotReadableException(HttpServletResponse response, HttpMessageNotReadableException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultCode(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 参数验证失败 - Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultCode handleMethodArgumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultCode(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 参数绑定失败- Bad Request
     */
    @ExceptionHandler(BindException.class)
    public ResultCode handleBindException(HttpServletResponse response, BindException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultCode(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 参数验证失败 - Bad Request
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultCode handleServiceException(HttpServletResponse response, ConstraintViolationException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultCode(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 参数验证失败 - Bad Request
     */
    @ExceptionHandler(ValidationException.class)
    public ResultCode handleValidationException(HttpServletResponse response, ValidationException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultCode(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 400 - 参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultCode processArgumentTypeMismatchException(HttpServletResponse response, MethodArgumentTypeMismatchException ex) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultCode(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 415 - 媒体类型不匹配
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultCode handleHttpMediaTypeNotSupportedException(HttpServletResponse response, HttpMediaTypeNotSupportedException ex) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ex.getMessage());
    }

    /**
     * 405 - 请求方法不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultCode handleHttpRequestMethodNotSupportedException(HttpServletResponse response, HttpRequestMethodNotSupportedException ex) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultCode(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage());
    }
}
