package com.wen.crowd.util;

/**
 * @author wen
 * @create 2021 1月 15 星期五 22:24
 * @description 统一整个项目中Ajax请求返回的结果（未来也可以用于分布式架构各个模块间调用时返回统一类型）
 */
public class ResultEntity<T> {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";
    //用来封装当前请求的结果是成功还是失败
    private String result;
    //请求失败时返回的错误消息
    private String message;
    //要返回的数据
    private T data;

    /**
     * 请求成功且不需要返回数据的工具方法
     *
     * @param <type> 请求的类型
     * @return ResultEntity
     */
    public static <type> ResultEntity<type> successWithoutData() {
        return new ResultEntity<type>(SUCCESS, null, null);
    }

    /**
     * 请求成功且需要返回数据的工具方法
     *
     * @param data   需要返回的数据
     * @param <type> 请求的类型
     * @return ResultEntity
     */
    public static <type> ResultEntity<type> successWithData(type data) {
        return new ResultEntity<type>(SUCCESS, null, data);
    }

    /**
     * 请求失败需要返回失败消息的工具方法
     *
     * @param message 失败返回的消息
     * @param <type>  请求的类型
     * @return ResultEntity
     */
    public static <type> ResultEntity<type> filed(String message) {
        return new ResultEntity<type>(FAILED, message, null);
    }

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
