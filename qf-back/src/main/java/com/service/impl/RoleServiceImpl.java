package com.service.impl;

import com.dao.RoleMapper;
import com.pojo.Role;
import com.pojo.request.AddRoleRequest;
import com.pojo.request.RoleRequest;
import com.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  17:45 2020/7/6
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public List<Role> selectAllRole(RoleRequest roleRequest) {
        return roleMapper.selectAllRole(roleRequest);
    }

    @Override
    public boolean updateRoleId(RoleRequest roleRequest) {
        return roleMapper.updateRoleId(roleRequest);
    }

    @Override
    public boolean addRole(AddRoleRequest addRoleRequest) {
        return roleMapper.addRole(addRoleRequest);
    }

    @Override
    public boolean empowerment(String roleId, List list) {
        //  先删除此角色的所有菜单项权限
        roleMapper.deleteEmpowerment(roleId);
        return roleMapper.empowerment(roleId, list);
    }
}
