package com.service.impl;

import com.dao.LoginMapper;
import com.pojo.Manager;
import com.pojo.Role;
import com.pojo.request.LoginRequest;
import com.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chengst
 * @date 2020/3/25
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public Manager login(LoginRequest loginRequest) {
        return loginMapper.login(loginRequest.getAccount());
    }
}
