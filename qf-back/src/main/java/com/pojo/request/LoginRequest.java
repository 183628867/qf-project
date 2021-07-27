package com.pojo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chengst
 * @date 2020/3/25
 **/
public class LoginRequest {

    @NotNull(message = "用户账号不能为空")
    private String account;

    @NotNull(message = "密码不能为空")
    private String password;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
