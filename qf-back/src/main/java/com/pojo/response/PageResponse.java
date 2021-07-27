package com.pojo.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.enums.ResponseEntity;

/**
 * 返回码
 *
 * @author chengst
 * @date 2020/3/25
 **/
public class PageResponse {

    /**
     * 错误码
     */
    @JSONField(serialize = false)
    private ResponseEntity responseEntity;


    private int code = -1;

    private String msg;


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

    public ResponseEntity getResponseEntity() {
        return responseEntity;
    }

    public void setResponseEntity(ResponseEntity responseEntity) {
        this.responseEntity = responseEntity;
        this.code = responseEntity.getCode();
        this.msg = responseEntity.getMsg();
    }
}
