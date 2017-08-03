package com.hualongdata.springstarter.common.exception;

/**
 * Internal Server Error
 * Created by yangbajing(yangbajing@gmail.com) on 2016-11-7.
 */
public class HlInternalServerErrorException extends HlBaseException {
    public static final int ERR_CODE = 500;

    public HlInternalServerErrorException(String errMsg) {
        this(ERR_CODE, errMsg, null);
    }

    public HlInternalServerErrorException(int errCode, String errMsg, Object data) {
        super(errCode, errMsg, data);
    }

}
