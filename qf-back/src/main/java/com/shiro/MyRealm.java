package com.shiro;

import com.pojo.Manager;
import com.pojo.Menu;
import com.pojo.Role;
import com.pojo.request.LoginRequest;
import com.service.LoginService;
import com.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * @Description
 * @Auth chengst
 * @Date Created in  17:39 2021-4-17
 */
public class MyRealm extends AuthorizingRealm {


    public void setName(String name) {
        super.setName("myRealm");
    }

    @Resource
    private LoginService loginService;

    @Resource
    private MenuService menuService;


    /**
     * 授权
     *
     * @param principalCollection 判断用户权限，先认证
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //  1. 获取已认证的用户数据
        Manager user = (Manager) principalCollection.getPrimaryPrincipal();
        //  2. 获取权限信息，角色 / 权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        String roleId = user.getRoleId();
        String roleName = user.getRoleName();

        List<Menu> menu = menuService.findMenus(roleId);

        //  角色
        HashSet<String> roles = new HashSet<>();
        roles.add(roleName);
        //  权限
        HashSet<String> perms = new HashSet<>();

        for (Menu m : menu) {
            if (StringUtils.isNotEmpty(m.getPath())) {
                perms.add(m.getPath());
            }
        }

        info.setStringPermissions(perms);
        info.setRoles(roles);
        System.out.println("角色权限--》" + info.getStringPermissions());
        return info;
    }


    /**
     * 身份认证
     *
     * @param authenticationToken
     * @return 传递的用户名和密码
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //  1. 获取登录的用户名和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //  2. 根据用户信息查询数据库
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAccount(username);
        Manager user = loginService.login(loginRequest);

        System.out.println(password);
        //  对象不为空，密码正确
        if (user != null) {
            //  安全数据，密码，realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
            return info;
        }

        return null;
    }
}
