package com.service.impl;

import com.dao.ManagerMapper;
import com.pojo.Manager;
import com.pojo.request.AddManagerRequest;
import com.pojo.request.ManagerRequest;
import com.pojo.request.UpdateManagerRequest;
import com.service.ManagerService;
import com.utils.EncdeStrUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  12:24 2020/4/25
 */
@Service
public class ManagerServviceImpl implements ManagerService {

    private static final Log log = LogFactory.getLog(LoginServiceImpl.class);

    @Resource
    private ManagerMapper managerMapper;

    @Override
    public boolean addUser(AddManagerRequest managerRequest) {
        try {
            //  默认密码123
            String ps = EncdeStrUtils.encrypt("123");
            managerRequest.setPassword(ps);
        } catch (Exception e) {
            log.error("密码加密出错");
        }
        return managerMapper.addManager(managerRequest);
    }

    @Override
    public List<Manager> findAllUser(ManagerRequest managerRequest) {
        return managerMapper.findAllManager(managerRequest);
    }

    @Override
    public Manager selectManagerById(Integer id) {
        return managerMapper.selectManagerById(id);
    }

    @Override
    public boolean updateUser(UpdateManagerRequest updateManagerRequest) {
        return managerMapper.updateManager(updateManagerRequest);
    }
}
