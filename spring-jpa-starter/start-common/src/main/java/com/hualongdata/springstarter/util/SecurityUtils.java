package com.hualongdata.springstarter.util;

import com.hualongdata.springstarter.common.domain.SaltPassword;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
public class SecurityUtils {
    private static final int SALT_LENGTH = 12;

    /**
     * 生成加密密码对象
     *
     * @param originalPassword 加密密码对象
     * @return 成功返回对象
     */
    public static SaltPassword saltPassword(String originalPassword) {
        byte[] password = originalPassword.getBytes(StandardCharsets.UTF_8);
        byte[] salt = Utils.nextBytes(SALT_LENGTH);
        byte[] target = new byte[SALT_LENGTH + password.length];
        System.arraycopy(salt, 0, target, 0, SALT_LENGTH);
        System.arraycopy(password, 0, target, SALT_LENGTH, password.length);
        byte[] saltPassword = DigestUtils.sha256(target);
        return new SaltPassword(salt, saltPassword);
    }

}
