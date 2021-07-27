package com.controller;

import com.enums.ResponseEntity;
import com.pojo.Manager;
import com.pojo.Menu;
import com.pojo.request.LoginRequest;
import com.pojo.response.LoginResponse;
import com.service.LoginService;
import com.service.MenuService;
import com.utils.EncdeStrUtils;
import com.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author chengst
 * @date 2020/3/25
 **/
@RestController
@RequestMapping("/api/login")
@Api(tags = "登录")
public class LoginController {


    @Resource
    private LoginService loginService;

    @Resource
    private MenuService menuService;


    @Autowired
    public static Log log = LogFactory.getLog(LoginController.class);

//    @Autowired
//    private JedisPool jedisPool;


    /**
     * 描述: 登陆
     *
     * @Author: chengst
     * @Date: 18:05 2020/4/15
     */
    @PostMapping("/loginIn")
    @ApiOperation("后台登陆接口")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult,HttpServletRequest request) {
        LoginResponse loginResponse = new LoginResponse();
        try {
            if (bindingResult.hasErrors()) {
                loginResponse.setMsg(bindingResult.getFieldError().getDefaultMessage());
                return loginResponse;
            }

            String password = EncdeStrUtils.encrypt(loginRequest.getPassword());
            log.info("用户输入的密码：" + password);
            Manager manager = loginService.login(loginRequest);
            if (manager == null) {
                loginResponse.setResponseEntity(ResponseEntity.LOGIN_NAME_FALSE);
                return loginResponse;
            }
            //  将用户输入的加密密码与数据库的密码对比
            if (!password.equals(manager.getPassword())) {
                loginResponse.setResponseEntity(ResponseEntity.LOGIN_PASSWORD_FALSE);
                return loginResponse;
            }
            String username = loginRequest.getAccount();
            UsernamePasswordToken upToken = new UsernamePasswordToken(username, password);
            //  1. 获取sebject
            Subject subject = SecurityUtils.getSubject();
            //  获取自定义sessionId
            String sessionId = subject.getSession().getId().toString();
            //  登录
            subject.login(upToken);
            //  登录成功
            if (subject.isAuthenticated()) {
                List<Menu> menuList = menuService.findMenu(manager.getRoleId());
                String token1 = TokenUtils.sign(manager.getAccount());
                loginResponse.setToken(token1);
                loginResponse.setManager(manager);
                loginResponse.setSessionId(sessionId);
                loginResponse.setMenuList(menuList);
                loginResponse.setResponseEntity(ResponseEntity.LOGIN_OK);
            } else {
                upToken.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
            loginResponse.setResponseEntity(ResponseEntity.LOGIN_FALSE);
        }
        return loginResponse;
    }

    /**
     * 描述: 退出登陆
     *
     * @Author: chengst
     * @Date: 15:46 2021-5-8
     */
    @PostMapping("/loginOut")
    @ApiOperation("退出登陆")
    public void loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

}
