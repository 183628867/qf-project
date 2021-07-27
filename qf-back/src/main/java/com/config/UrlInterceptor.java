package com.config;


import com.utils.TokenUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:   拦截器
 *
 * @Author: chengst
 */
@Component
@Configuration
public class UrlInterceptor implements HandlerInterceptor {

    private static final Log log = LogFactory.getLog(UrlInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("--------------拦截器 start-------------");
        log.info(request.getRequestURI());
        log.info(request.getMethod());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        String token = request.getHeader("token");
        log.info("token----" + token);
        //  判断是否有token
        if (token == null) {
            log.info("-------无token信息，请重新登录----");
            response.getWriter().write("{\"code\":\"1001\",\"msg\":\"无token信息,请重新登录!\"}");
            return false;
        }
        //  校验token
        boolean flag = TokenUtils.verify(token);
        if (!flag) {
            log.info("---令牌无效---");
//            response.getWriter().println("token令牌无效,请重新登录!");
            response.getWriter().write("{\"code\":\"1002\",\"msg\":\"token令牌无效,请重新登录!\"}");
            return false;
        }
        log.info("--------------拦截器 end-------------");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
