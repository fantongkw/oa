package com.ccc.oa.utils;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.Serializable;

public class ResultMessage<T> implements Serializable {
    private static final long serialVersionUID = 1155225553961238619L;
    private int status;
    private String msg;
    private T data;

    public ResultMessage(HttpStatus status) {
        Assert.notNull(status, "HttpStatus must not be null");
        this.status = status.value();
        this.msg = status.getReasonPhrase();
    }

    public ResultMessage(HttpStatus status, @Nullable T data) {
        Assert.notNull(status, "HttpStatus must not be null");
        this.status = status.value();
        this.msg = status.getReasonPhrase();
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
