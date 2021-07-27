package com.pojo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auth chengst
 * @Date Created in  17:42 2020/7/6
 */
public class RoleRequest extends PageRequest {

    private String roleId;

    private String roleName;

    private String menuId;

    private String deleteFlag;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
