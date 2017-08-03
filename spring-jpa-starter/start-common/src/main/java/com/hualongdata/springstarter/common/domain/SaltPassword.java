package com.hualongdata.springstarter.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaltPassword {
    private byte[] salt;
    private byte[] saltPassword;
}
