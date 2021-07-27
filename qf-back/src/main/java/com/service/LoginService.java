package com.service;

import com.pojo.Manager;
import com.pojo.Role;
import com.pojo.request.LoginRequest;

import java.util.List;

/**
 * @author chengst
 * @date 2020/3/25
 **/
public interface LoginService {


    /*
     *  登陆
     * @author chengst
     * @Date  2020/3/25 11:13
     * @version 1.0.0
     **/
    public Manager login(LoginRequest loginRequest);
}
