package com.hualongdata.springstarter.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2016-09-9.
 */
@JsonIgnoreProperties(value = {"stackTrace", "suppressed", "message", "localizedMessage"})
public abstract class HlBaseException extends RuntimeException {
    private int errCode;
    private String errMsg;
    private Object data;

    public HlBaseException(int errCode, String errMsg) {
        this(errCode, errMsg, null);
    }

    public HlBaseException(int errCode, String errMsg, Object data) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
