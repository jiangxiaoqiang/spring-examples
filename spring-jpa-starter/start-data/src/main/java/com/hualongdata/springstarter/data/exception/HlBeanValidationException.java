package com.hualongdata.springstarter.data.exception;

import com.hualongdata.springstarter.common.exception.HlBadRequestException;
import com.hualongdata.springstarter.common.exception.HlBaseException;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
public class HlBeanValidationException extends HlBaseException {
    public static final int ERR_CODE = HlBadRequestException.ERR_CODE;

    public HlBeanValidationException(String errMsg, BindingResult result) {
        super(ERR_CODE, errMsg);
        Map<String, String> data = new HashMap<>();
        result.getFieldErrors()
                .forEach(field -> data.put(field.getField(), field.getDefaultMessage()));
        setData(data);
    }

}
