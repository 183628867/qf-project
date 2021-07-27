package com.dao;

import com.pojo.Role;
import com.pojo.request.AddRoleRequest;
import com.pojo.request.RoleRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> selectAllRole(RoleRequest roleRequest);

    /**
     * 描述: 删除角色
     *
     * @Author: chengst
     * @Date: 16:03 2020/7/14
     */
    boolean updateRoleId(RoleRequest roleRequest);

    /**
     *
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
    boolean empowerment(@Param("roleId") String roleId, @Param("list") List list);


    /**
     * 描述: 在给此角色添加权限之前将他的权限删除
     *
     * @Author: chengst
     * @Date: 9:54 2020/7/13
     */
    boolean deleteEmpowerment(String roleId);
}