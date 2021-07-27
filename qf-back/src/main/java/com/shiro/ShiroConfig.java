package com.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description
 * @Auth chengst
 * @Date Created in  14:01 2021-4-7
 */
@Configuration
public class ShiroConfig {

    //  1. 创建realm
    @Bean
    public MyRealm getRealm() {
        return new MyRealm();
    }

    //  2. 创建安全管理器
    @Bean
    public SecurityManager getSecurityManager(MyRealm myRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);

        //  将自定义的安全管理器注册到安全管理器中
        defaultWebSecurityManager.setSessionManager(sessionManager());
        //  将自定义的redis缓存管理器注册到安全管理器中
        defaultWebSecurityManager.setCacheManager(redisCacheManager());
        return defaultWebSecurityManager;
    }


    //  3. 配置shiro过滤器工厂
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        //  1. 创建过滤器工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //自定义拦截器限制并发人数,参考博客
        LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        //限制同一帐号同时在线的个数
        filtersMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        //  2. 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //  3. 通用设置（跳转登陆页面，未授权页面）
        shiroFilterFactoryBean.setLoginUrl("/autherror?code=1");
        shiroFilterFactoryBean.setUnauthorizedUrl("/autherror?code=2");

        //  4. 设置过滤器集合
        Map<String, String> filterMap = new LinkedHashMap<>();

        // login接口不需要认证
        filterMap.put("/api/login/loginIn", "anon");


        filterMap.put("/user/home", "anon");//   当前地址可以匿名访问

        //  具有某种权限才能访问
//        filterMap.put("/api/login/user/home", "perms[user-home]");
        //  具有某种角色才能访问
//        filterMap.put("/api/login/user/home", "roles[系统管理员]");

        filterMap.put("/user/**", "authc");//    需要认证

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }


    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;

    //  redis的控制器，操作redis
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setPassword(password);
        return redisManager;
    }


    //  配置sessionDao
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }


    //  自定义绘画管理器
    public DefaultWebSessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }


    //  缓存管理器
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


    //  4. 开启对shiro注解的支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 并发登录控制
     * @return
     */
    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter(){
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        //用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        kickoutSessionControlFilter.setCacheManager(redisCacheManager());
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
        kickoutSessionControlFilter.setKickoutAfter(false);
        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        kickoutSessionControlFilter.setMaxSession(1);
        //被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("http://www.baidu.com");
        return kickoutSessionControlFilter;
    }

}
