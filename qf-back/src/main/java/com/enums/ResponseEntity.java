package com.enums;

import java.io.Serializable;

/**
 * 返回枚举
 */
public enum ResponseEntity implements Serializable {


    CM_OK(0, "成功！"),
    CM_FALSE(1, "失败！"),
    LOGIN_OK(0, "登陆成功！"),
    LOGIN_FALSE(-1, "登陆失败！"),
    LOGIN_NAME_FALSE(-2, "账号不存在或已被禁用!"),
    LOGIN_PASSWORD_FALSE(-3, "账号密码错误"),
    EXCEPTIO_SYSTEM(-4, "服务器异常"),
    EXCEPTION_REQUEST(-5, "请求方式不对"),
    EXCEPTION_NULL(-6, "空指针异常"),
    ACCOUNT_BEIND_USED_FALSE(-7, "此账号已被使用"),
    NOT_HAVE_PERSISSION(-8, "无权限"),
    NOT_HAVE_PERSISSION_LOGIN(-9, "系统检测到未登录或登录超时,请重新登陆"),
    SHIRO_UNKNOW_USERNAME(-10, "未知账户"),
    SHIRO_PASSWORD_FALSE(-11, "shiro密码不正确"),
    SHIRO_LOCK_USERNAME(-12, "账户已锁定"),
    SHIRO_PASSWORD_FLASE_MORE(-13, "用户名或密码错误次数过多"),
    REDIS_CONNECT_TIME_OUT(-14, "redis连接失败   connect timed out"),
    DELETE_MENU_FALSE(-15, "删除菜单失败,请先删除子菜单"),
    UPDATE_PASSWORD_OLD_FALSE(-16, "原密码错误"),
    UPDATE_PASSWORD_MANAGER_NULL(-17, "系统未检测到账户"),
    EXCEPTIO_SYSTEM_FALSE(-18,"演示环境,不允许操作")
    ;

    private int code;

    private String msg;

    private ResponseEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
