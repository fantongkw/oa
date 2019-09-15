package com.ccc.oa.Exception;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 522662011534418010L;
    private String msg;

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
