package com.hualongdata.springstarter.common.exception;

/**
 * Not Found
 * Created by yangbajing(yangbajing@gmail.com) on 2016-11-7.
 */
public class HlNotFoundException extends HlBaseException {
    public static final int ERR_CODE = 404;

    public HlNotFoundException(String errMsg) {
        this(ERR_CODE, errMsg, null);
    }

    public HlNotFoundException(int errCode, String errMsg, Object data) {
        super(errCode, errMsg, data);
    }
}
