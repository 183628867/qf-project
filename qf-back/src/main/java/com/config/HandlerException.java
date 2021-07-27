package com.config;

import com.enums.ExceptionEntity;
import com.enums.ResponseEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * 全局异常处理器
 *
 * @Auth chengst
 * @Date Created in  11:32 2020/5/27
 */
@ResponseBody
@ControllerAdvice

public class HandlerException {

    private static final Log log = LogFactory.getLog(HandlerException.class);


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionEntity HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        log.error("请求方式不对");
        e.printStackTrace();
        exceptionEntity.setResponseEntity(ResponseEntity.EXCEPTION_REQUEST);
        return exceptionEntity;
    }


    @ExceptionHandler(Exception.class)
    public ExceptionEntity Exception(Exception e) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        log.error("服务器异常", e);
        exceptionEntity.setResponseEntity(ResponseEntity.EXCEPTIO_SYSTEM);
        return exceptionEntity;
    }


    @ExceptionHandler(UndeclaredThrowableException.class)
    public ExceptionEntity UndeclaredThrowableException() {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setResponseEntity(ResponseEntity.EXCEPTIO_SYSTEM_FALSE);
        return exceptionEntity;
    }


    @ExceptionHandler(NullPointerException.class)
    public ExceptionEntity NullPointerException(NullPointerException e) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        log.error("空指针异常" + e);
        exceptionEntity.setResponseEntity(ResponseEntity.EXCEPTION_NULL);
        return exceptionEntity;
    }

    //  shiro未登录
    @ExceptionHandler(AuthorizationException.class)
    public ExceptionEntity handleAuthorizationException(AuthorizationException e) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        log.error(e);
        exceptionEntity.setResponseEntity(ResponseEntity.NOT_HAVE_PERSISSION_LOGIN);
        return exceptionEntity;
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ExceptionEntity UnauthorizedException(UnauthorizedException e) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        log.error("无权限");
        exceptionEntity.setResponseEntity(ResponseEntity.NOT_HAVE_PERSISSION);
        return exceptionEntity;
    }

    @ExceptionHandler(UnknownAccountException.class)
    public ExceptionEntity UnknownAccountException(UnknownAccountException e) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        log.error("未知账户");
        exceptionEntity.setResponseEntity(ResponseEntity.SHIRO_UNKNOW_USERNAME);
        return exceptionEntity;
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ExceptionEntity IncorrectCredentialsException(IncorrectCredentialsException e) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        log.error("shiro密码不正确");
        exceptionEntity.setResponseEntity(ResponseEntity.SHIRO_PASSWORD_FALSE);
        return exceptionEntity;
    }

    @ExceptionHandler(LockedAccountException.class)
    public ExceptionEntity LockedAccountException(LockedAccountException e) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        log.error("账户已锁定");
        exceptionEntity.setResponseEntity(ResponseEntity.SHIRO_LOCK_USERNAME);
        return exceptionEntity;
    }

    @ExceptionHandler(ExcessiveAttemptsException.class)
    public ExceptionEntity ExcessiveAttemptsException(ExcessiveAttemptsException e) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        log.error("用户名或密码错误次数过多");
        exceptionEntity.setResponseEntity(ResponseEntity.SHIRO_PASSWORD_FLASE_MORE);
        return exceptionEntity;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ExceptionEntity AuthenticationException(AuthenticationException e) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        log.error("用户名或密码不正确");
        exceptionEntity.setResponseEntity(ResponseEntity.LOGIN_PASSWORD_FALSE);
        return exceptionEntity;
    }

    @ExceptionHandler(JedisConnectionException.class)
    public ExceptionEntity AuthenticationException(JedisConnectionException e) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        log.error("redis连接失败");
        exceptionEntity.setResponseEntity(ResponseEntity.REDIS_CONNECT_TIME_OUT);
        return exceptionEntity;
    }

}
