package com.service;

import com.pojo.Role;
import com.pojo.request.AddRoleRequest;
import com.pojo.request.RoleRequest;

import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  17:44 2020/7/6
 */
public interface RoleService {


    /**
     * 描述: 查询所有的角色
     *
     * @Author: chengst
     * @Date: 17:46 2020/7/6
     */
    List<Role> selectAllRole(RoleRequest roleRequest);


    /**
     * 描述: 删除角色
     *
     * @Author: chengst
     * @Date: 16:03 2020/7/14
     */
    boolean updateRoleId(RoleRequest roleRequest);

    /**
     * 添加角色
     * @param addRoleRequest
     * @return
     */
    boolean addRole(AddRoleRequest addRoleRequest);


    /**
     * 描述: 批量添加/修改角色所能访问的菜单路径(赋权)
     *
     * @Author: chengst
     * @Date: 9:42 2020/7/13
     */
    boolean empowerment(String roleId, List list);
}
