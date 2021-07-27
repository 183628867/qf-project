package com.enums;

/**
 * @Description
 * @Auth chengst
 * @Date Created in  10:40 2021-3-16
 */
public class RetResult<T> {

    public int code;

    private String msg;

    private T data;

    public RetResult<T> setCode(ResponseEntity responseEntity) {
        this.code = responseEntity.getCode();
        return this;
    }

    public int getCode() {
        return code;
    }

    public RetResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RetResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RetResult<T> setData(T data) {
        this.data = data;
        return this;
    }

}
