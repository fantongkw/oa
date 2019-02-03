package com.ccc.oa.utils;

import java.io.Serializable;

public class ResultMsg implements Serializable {
    private static final long serialVersionUID = 1155225553961238619L;
    private int error;
    private String msg;
    private Object data;

    public ResultMsg(){}

    public ResultMsg(int error, String msg) {
        this.error = error;
        this.msg = msg;
    }

    public ResultMsg(int error, String msg, Object data) {
        this.error = error;
        this.msg = msg;
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
