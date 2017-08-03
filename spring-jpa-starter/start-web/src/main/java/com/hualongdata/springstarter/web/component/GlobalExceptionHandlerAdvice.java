package com.hualongdata.springstarter.web.component;

import com.hualongdata.springstarter.common.domain.ApiResult;
import com.hualongdata.springstarter.common.exception.HlBaseException;
import com.hualongdata.springstarter.data.exception.HlBeanValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 全局异常拦截
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
@ControllerAdvice(basePackages = "com.hualongdata.springstarter.web.controller")
public class GlobalExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manvEx = (MethodArgumentNotValidException) ex;
            HlBeanValidationException badException =
                    new HlBeanValidationException(manvEx.getMessage(), manvEx.getBindingResult());
            return new ResponseEntity<>(badException, HttpStatus.BAD_REQUEST);
        }

        logger.error("系统错误", ex);
        return new ResponseEntity<>(ApiResult.error(status.value(), "系统错误"), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 重设HlBaseException为返回200状态码，并输出用户用好的JSON内容：{"errCode":500,"errMsg":""}
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(HlBaseException.class)
    @ResponseBody
    ResponseEntity<?> handleSBaseException(HttpServletRequest request, HlBaseException ex) {
        logger.debug(ex.toString());
        return new ResponseEntity<>(ApiResult.error(ex.getErrCode(), ex.getErrMsg(), ex.getData()), getStatus(ex.getErrCode()));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    ResponseEntity<?> handleRuntimeException(HttpServletRequest request, RuntimeException ex) {
        HttpStatus status = getStatus(request);
        String errMsg = "运行异常";

        if (ex instanceof DataAccessException) {
            errMsg = "数据库操作异常";
        }

        logger.error(errMsg, ex);
        ApiResult errResp = ApiResult.error(status.value(), errMsg, ex.getCause());
        return new ResponseEntity<>(errResp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatus getStatus(int statusCode) {
        return Arrays.stream(HttpStatus.values())
                .filter(status -> status.value() == statusCode)
                .findFirst()
                .orElse(HttpStatus.BAD_REQUEST);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
