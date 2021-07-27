package com.pojo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Description
 * @Auth chengst
 * @Date Created in  16:17 2021-4-8
 */
public class AddRoleRequest {


    @NotEmpty(message = "名字不能为空")
    private String roleName;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
