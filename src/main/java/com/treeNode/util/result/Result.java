package com.treeNode.util.result;


/*
 * 请求结果封装类
 */
public class Result<T> {
    private static final long serialVersionUID = -1415151535388L;

    //是否成功: 成功(true),失败(false)
    private boolean success;
    //业务编码
    private String code;
    //业务信息
    private String message;
    //具体数据
    private T info;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
