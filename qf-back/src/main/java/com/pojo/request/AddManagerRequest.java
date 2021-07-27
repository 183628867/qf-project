package com.pojo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Description
 * @Auth chengst
 * @Date Created in  21:09 2021-4-10
 */
public class AddManagerRequest {
    @NotEmpty(message = "名称不能为空")
    private String name;

    @NotEmpty(message = "性别不能为空")
    private String sex;
    @NotEmpty(message = "账号不能为空")
    private String account;

    private String image;

    private String password;

    private String insertDt;

    private String roleId;


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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
