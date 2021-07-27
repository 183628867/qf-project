package com.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enums.ResponseEntity;
import com.google.api.client.util.Lists;
import com.pojo.Role;
import com.pojo.request.AddRoleRequest;
import com.pojo.request.RoleRequest;
import com.pojo.response.RoleResponse;
import com.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  17:32 2020/7/6
 */
@RestController
@RequestMapping("/api/role/")
public class RoleController {

    @Resource
    private RoleService roleService;


    /**
     * 描述: 查询除所有角色
     *
     * @Author: chengst
     * @Date: 17:54 2020/7/6
     */
    @GetMapping("selectAllRole")
    public RoleResponse selectAllRole(RoleRequest roleRequest) {
        RoleResponse resposne = new RoleResponse();
        List<Role> roles = roleService.selectAllRole(roleRequest);
        resposne.setRoleList(roles);
        resposne.setResponseEntity(ResponseEntity.CM_OK);
        return resposne;
    }


    /**
     * 描述: 删除角色
     *
     * @Author: chengst
     * @Date: 16:04 2020/7/14
     */
    @PostMapping("updateRoleId")
    public RoleResponse updateRoleId(RoleRequest roleRequest) {
        RoleResponse resposne = new RoleResponse();
        boolean flag = roleService.updateRoleId(roleRequest);
        if (flag) {
            resposne.setResponseEntity(ResponseEntity.CM_OK);
        }
        return resposne;
    }

    /**
     * 描述: 添加角色
     *
     * @Author: chengst
     * @Date: 16:04 2020/7/14
     */
    @PostMapping("addRole")
    public RoleResponse addRole(AddRoleRequest addRoleRequest) {
        RoleResponse resposne = new RoleResponse();
        boolean flag = roleService.addRole(addRoleRequest);
        if (flag) {
            resposne.setResponseEntity(ResponseEntity.CM_OK);
        }
        return resposne;
    }


    /**
     * 描述: 给此角色赋权
     *
     * @Author: chengst
     * @Date: 10:06 2020/7/13
     */
    @PostMapping("empowerment")
    @Transactional(rollbackFor = Exception.class)
    public RoleResponse empowerment(@RequestBody String params) {
        RoleResponse roleResponse = new RoleResponse();
        JSONObject jsonObject = JSONObject.parseObject(params);
        String roleId = jsonObject.getString("roleId");
        String[] menuId = jsonObject.getString("list").split(",");
        List list = new ArrayList<>();
        for (int i = 0; i < menuId.length; i++) {
            list.add(menuId[i]);
        }
        boolean flag = roleService.empowerment(roleId, list);
        if (flag) {
            roleResponse.setResponseEntity(ResponseEntity.CM_OK);
        }
        return roleResponse;
    }
}


