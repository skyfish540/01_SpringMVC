package com.bjpowernode.commons;

/**
 *这个类作为一个工具类，它的作用是提供统一的JSON返回对象
 */
public class CommonsReturnObject {
        //Json的返回编码 取值任意但最好统一，例如返回码为0表示请求成功 返回码为1表示请求失败
        //它类似我们Http请求中的200 表示成功 404表示资源不存在等等
        private String code;
        //错误的返回信息
        private String errorMessage;
        //统一的返回数据对象
        private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
