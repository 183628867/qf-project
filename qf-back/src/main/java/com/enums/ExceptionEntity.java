package com.enums;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Auth chengst
 * @Date Created in  12:18 2020/5/27
 */
public class ExceptionEntity{

    @JSONField(serialize = false)
    private ResponseEntity responseEntity;

    private int code;

    private String msg;

    public ResponseEntity getResponseEntity() {
        return responseEntity;
    }

    public void setResponseEntity(ResponseEntity responseEntity) {
        this.responseEntity = responseEntity;
        this.code = responseEntity.getCode();
        this.msg = responseEntity.getMsg();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
