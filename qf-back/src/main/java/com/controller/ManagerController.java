package com.controller;

import com.enums.ResponseEntity;
import com.pojo.Manager;
import com.pojo.request.AddManagerRequest;
import com.pojo.request.LoginRequest;
import com.pojo.request.ManagerRequest;
import com.pojo.request.UpdateManagerRequest;
import com.pojo.response.ManagerReponse;
import com.service.LoginService;
import com.service.ManagerService;
import com.utils.EncdeStrUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  12:28 2020/4/25
 */
@RestController
@RequestMapping("/api/manager/")
public class ManagerController {


    @Resource
    private ManagerService managerService;

    @Resource
    private LoginService loginService;


    /**
     * 描述: 添加后台用户
     *
     * @Author: chengst
     * @Date: 11:04 2021-4-15
     */
    @PostMapping("addUser")
    public ManagerReponse addUser(@Valid AddManagerRequest managerRequest, BindingResult bindingResult) {
        ManagerReponse managerReponse = new ManagerReponse();
        if (bindingResult.hasErrors()) {
            managerReponse.setMsg(bindingResult.getFieldError().getDefaultMessage());
            return managerReponse;
        }
        LoginRequest loginRequest = new LoginRequest();
        //  判断账号是否已存在
        loginRequest.setAccount(managerRequest.getAccount());
        Manager manager = loginService.login(loginRequest);
        if (manager != null) {
            managerReponse.setResponseEntity(ResponseEntity.ACCOUNT_BEIND_USED_FALSE);
            return managerReponse;
        }
        boolean flag = managerService.addUser(managerRequest);
        if (flag) {
            managerReponse.setResponseEntity(ResponseEntity.CM_OK);
        }
        return managerReponse;
    }

    /**
     * 描述: 查询所有后台用户
     *
     * @Author: chengst
     * @Date: 11:04 2021-4-15
     */
    @RequiresPermissions("managerList")
    @GetMapping("/findAllUser")
    public ManagerReponse findAllUser(ManagerRequest managerRequest) {
        ManagerReponse managerReponse = new ManagerReponse();
        List<Manager> allUser = managerService.findAllUser(managerRequest);
        managerReponse.setUserList(allUser);
        managerReponse.setResponseEntity(ResponseEntity.CM_OK);
        return managerReponse;
    }

    /**
     * 描述: 修改后台用户
     *
     * @Author: chengst
     * @Date: 11:04 2021-4-15
     */
    @PostMapping("/updateUser")
    public ManagerReponse updateUser(UpdateManagerRequest updateManagerRequest) {
        ManagerReponse managerReponse = new ManagerReponse();
        Boolean flag = managerService.updateUser(updateManagerRequest);
        if (flag) {
            managerReponse.setResponseEntity(ResponseEntity.CM_OK);
        }
        return managerReponse;
    }


    @PostMapping("updatePassword")
    public ManagerReponse updatePassword(UpdateManagerRequest updateManagerRequest) throws Exception{
        ManagerReponse managerReponse = new ManagerReponse();
        //  通过id查询出此账户的所有信息
        Manager manager = managerService.selectManagerById(updateManagerRequest.getId());
        if(manager == null){
            managerReponse.setResponseEntity(ResponseEntity.UPDATE_PASSWORD_MANAGER_NULL);
            return managerReponse;
        }
        //  对比原先正确的密码和加密后传过来的老密码
        String password = manager.getPassword();
        String oldPass = EncdeStrUtils.encrypt(updateManagerRequest.getOldPass());
        if(!password.equals(oldPass)){
            managerReponse.setResponseEntity(ResponseEntity.UPDATE_PASSWORD_OLD_FALSE);
            return managerReponse;
        }
        //  将新密码加密
        String newPassword = EncdeStrUtils.encrypt(updateManagerRequest.getNewPass());
        updateManagerRequest.setPassword(newPassword);
        Boolean flag = managerService.updateUser(updateManagerRequest);
        if (flag) {
            managerReponse.setResponseEntity(ResponseEntity.CM_OK);
        }
        return managerReponse;
    }

}
