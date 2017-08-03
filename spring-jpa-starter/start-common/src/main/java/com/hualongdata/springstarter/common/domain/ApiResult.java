package com.hualongdata.springstarter.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
@Data
@ApiModel(value = "ApiResult", description = "Api返回结果")
public class ApiResult {
    @ApiModelProperty(value = "错误码")
    private int errCode = 0;

    @ApiModelProperty(value = "错误消息")
    private String errMsg = "";

    @ApiModelProperty(value = "返回数据")
    private Object data = null;

    private ApiResult() {
    }

    public static ApiResult success() {
        return success(null);
    }

    public static ApiResult success(Object data) {
        return error(0, "", data);
    }

    public static ApiResult error(int code, String message) {
        ApiResult resp = new ApiResult();
        resp.setErrCode(code);
        resp.setErrMsg(message);
        return resp;
    }

    public static ApiResult error(int code, String message, Object data) {
        ApiResult resp = new ApiResult();
        resp.setErrCode(code);
        resp.setErrMsg(message);
        resp.setData(data);
        return resp;
    }

    public static ApiResult error(String message) {
        return error(500, message);
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
