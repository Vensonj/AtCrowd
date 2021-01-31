package com.wen.crowd.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wen
 * @create 2021 1月 15 星期五 23:24
 * @description 判断发送的请求是普通类型还是Ajax请求 返回true代表是Ajax请求，反之是普通请求
 */
public class JudgeRequestType {
    public static boolean judgeRequestType(HttpServletRequest request) {
        //获取请求头消息
        String acceptHeader = request.getHeader("Accept");
        String xRequestedHeader = request.getHeader("X-Requested-With");
        //判断
        return acceptHeader.contains("application/json") || xRequestedHeader.equals("XMLRequest");
    }
}
