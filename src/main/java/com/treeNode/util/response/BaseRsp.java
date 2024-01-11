package com.treeNode.util.response;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class BaseRsp<T> extends BaseRspHeader {
    private T data;

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
