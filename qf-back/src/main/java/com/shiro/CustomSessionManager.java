package com.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @Description 自定义manager
 * @Auth chengst
 * @Date Created in  9:50 2021-4-19
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    /**
     * 请求头 Authorization
     * 重写。，指定sessionId的获取方式
     */
    @Override
    public Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //  获取请求头中的Authorization
        String authorization = WebUtils.toHttp(request).getHeader("Authorization");
        System.out.println("请求头中的" + authorization);
        if (StringUtils.isEmpty(authorization)) {
            //  如果没有携带，生成新的sessionId
            return super.getSessionId(request, response);
        } else {
            //  返回sessionId,
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "cookie");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, authorization);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return authorization;
        }
    }
}
