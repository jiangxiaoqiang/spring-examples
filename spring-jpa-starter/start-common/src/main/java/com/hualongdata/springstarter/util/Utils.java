package com.hualongdata.springstarter.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
public class Utils {

    /**
     * 获取随机字节数组
     *
     * @param size 字节数组长度
     * @return 随机字节数组
     */
    public static byte[] nextBytes(int size) {
        byte[] bytes = new byte[size];
        ThreadLocalRandom.current().nextBytes(bytes);
        return bytes;
    }

}
