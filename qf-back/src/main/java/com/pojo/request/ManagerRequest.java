package com.pojo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Auth chengst
 * @Date Created in  12:22 2020/4/25
 */
public class ManagerRequest extends PageRequest {
    private String name;

    private String sex;
    private String account;

    private String image;

    private String password;

    private String insertDt;
    private String role;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInsertDt() {
        return insertDt;
    }

    public void setInsertDt(String insertDt) {
        this.insertDt = insertDt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
