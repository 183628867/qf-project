package com.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;

/**
 * 用户表
 *
 * @author chengst
 * @date 2020/3/25
 **/
//  AuthCachePrincipal redis和插件包提供的接口
@JsonInclude(JsonInclude.Include.NON_NULL)  //  此注解得作用是把差出来为null得数据不显示在前端
public class Manager implements Serializable, AuthCachePrincipal {

    private Integer id;

    private String name;

    private String account;

    private String password;

    private String insertDt;

    private String img;

    private String sex;

    private String disable;

    private String roleId;

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
