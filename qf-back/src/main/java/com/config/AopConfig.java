package com.config;

import com.enums.*;
import com.pojo.request.LoginRequest;
import com.service.LogService;
import com.utils.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @Auth chengst
 * @Date Created in  15:11 2020/7/4
 */
@Aspect
@Component
public class AopConfig {

    public static Log log = LogFactory.getLog(AopConfig.class);

    @Resource
    private LogService logService;


    //  切面点
    private final String POINT_CUT = "execution(public * com.controller..*.*(..))";

    /**
     * 切入点
     */
    @Pointcut("execution(public * com.controller.LoginController.*(..))")
    public void log() {

    }


    @Pointcut(POINT_CUT)
    private void AopConfig() {
    }


    @Before("AopConfig()")
    public void before(JoinPoint joinPoint) throws Exception {
        String name = joinPoint.getSignature().getName();
        System.out.println("目标方法名为:" + name);
        System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        if ("addUser".equals(name) || "updateUser".equals(name) || "updateRoleId".equals(name) || "addRole".equals(name) || "addMenu".equals(name) || "updateMenu".equals(name) || "deleteMenu".equals(name)) {
            throw new Exception("演示环境,不允许操作");
        }
    }

    /**
     * 正常返回通知，拦截service层记录用户正常的日志
     *
     * @param joinPoint
     */
    @After("log()")
    public void doAfter(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        //获取参数
        LoginRequest param = (LoginRequest) joinPoint.getArgs()[0];

        //获取IP地址
        String ip = IpUtils.getIpAddr(request);
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        String browser = userAgent.getBrowser().toString();
        String systemMc = userAgent.getOperatingSystem().toString();

        logService.insertLoginLog(param.getAccount(), ip, browser, systemMc);
    }
}
