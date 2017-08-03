package com.hualongdata.springstarter.common.exception;

/**
 * 400 Bad Request
 * Created by yangbajing(yangbajing@gmail.com) on 2016-09-9.
 */
public class HlBadRequestException extends HlBaseException {
    public static final int ERR_CODE = 400;

    public HlBadRequestException(String errMsg) {
        this(ERR_CODE, errMsg, null);
    }

    public HlBadRequestException(int errCode, String errMsg) {
        this(errCode, errMsg, null);
    }

    public HlBadRequestException(int errCode, String errMsg, Object data) {
        super(errCode, errMsg, data);
    }
}
