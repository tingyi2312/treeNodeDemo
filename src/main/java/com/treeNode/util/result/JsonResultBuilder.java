package com.treeNode.util.result;
/*
 * result封装处理类
 */
public class JsonResultBuilder {
    public static <T> SimpleJsonResult<T> simpleSucc(T info) {
        SimpleJsonResult<T> result = new SimpleJsonResult<>();
        result.setSuccess(true);
        result.setInfo(info);
        return result;
    }

    public static <T> SimpleJsonResult<T> simpleSucc() {
        SimpleJsonResult<T> result = new SimpleJsonResult<>();
        result.setSuccess(true);
        return result;
    }

    public static <T> SimpleJsonResult<T> resultFail(String code, String message) {
        SimpleJsonResult<T> result = new SimpleJsonResult<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> PageJsonResult<T> pageSucc(T info) {
        PageJsonResult<T> result = new PageJsonResult<>();
        result.setSuccess(true);
        result.setInfo(info);
        return result;
    }

    public static <T> PageJsonResult<T> pageSucc(T info, int page, int pageSize, int totalSize) {
        PageJsonResult<T> result = new PageJsonResult<>();
        result.setSuccess(true);
        result.setInfo(info);
        result.calculate(page, pageSize, totalSize);
        return result;
    }

    public static <T> PageJsonResult<T> pageFail(String code, String message) {
        PageJsonResult<T> result = new PageJsonResult<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
