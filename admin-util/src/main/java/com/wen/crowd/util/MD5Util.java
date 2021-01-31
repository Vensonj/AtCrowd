package com.wen.crowd.util;

import com.wen.crowd.constant.CrowdConstant;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * @author wen
 * @create 2021 1月 17 星期日 21:09
 * @description 对明文字符串进行MD5加密
 */
public class MD5Util {
    public static String md5(String srcPwd) {
        // 判断srcPwd是否是有效的字符串
        if (srcPwd == null || srcPwd.length() == 0) {
            // 如果不是有效的字符串抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_INVALID_STRING);
        }

        try {
            // 获取MessageDigest对象(JDK提供内置对象)
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 获取明文字符串对应的字节数组
            byte[] input = srcPwd.getBytes(StandardCharsets.UTF_8);
            // 执行加密
            byte[] output = messageDigest.digest(input);
            // 创建BigInteger对象
            int signum = 1; // -1代表负数 0代表0 1 代表正数
            BigInteger bigInteger = new BigInteger(signum, output);
            // 按照16进制将bigInteger的值转换为字符串
            int radix = 16;
            return bigInteger.toString(radix).toUpperCase(Locale.ROOT);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
