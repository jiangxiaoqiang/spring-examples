package com.hualongdata.springstarter.common.exception;

/**
 * 功能未实现
 * Created by yangbajing(yangbajing@gmail.com) on 2016-11-22.
 */
public class HlNotImplementException extends HlBaseException {
    public static final int ERR_CODE = 501;

    public HlNotImplementException(String errMsg) {
        this(ERR_CODE, errMsg, null);
    }

    public HlNotImplementException(int errCode, String errMsg, Object data) {
        super(errCode, errMsg, data);
    }

    public HlNotImplementException() {
        this("未实现");
    }
}
