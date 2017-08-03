package com.hualongdata.springstarter.common.exception;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2016-09-9.
 */
public class HlUnauthorizedException extends HlBaseException {
    public final static int ERR_CODE = 401;

    public HlUnauthorizedException() {
        this(ERR_CODE, "认证会话不存在，您需要登录访问", null);
    }

    public HlUnauthorizedException(String message) {
        this(ERR_CODE, message, null);
    }

    public HlUnauthorizedException(int errCode,String message) {
        this(errCode, message, null);
    }

    public HlUnauthorizedException(int errCode, String errMsg, Object data) {
        super(errCode, errMsg, data);
    }
}
