package com.hualongdata.springstarter.common.exception;

/**
 * Created by Gang.Zou on 17-2-7.
 */
public class HlForbiddenException extends HlBaseException {
    public static final int ERR_CODE = 403;

    public HlForbiddenException(String errMsg) {
        this(ERR_CODE, errMsg, null);
    }

    public HlForbiddenException(int errCode, String errMsg) {
        this(errCode, errMsg, null);
    }

    public HlForbiddenException(int errCode, String errMsg, Object data) {
        super(errCode, errMsg, data);
    }

}