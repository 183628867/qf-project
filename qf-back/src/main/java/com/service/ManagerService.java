package com.service;

import com.pojo.Manager;
import com.pojo.request.AddManagerRequest;
import com.pojo.request.ManagerRequest;
import com.pojo.request.UpdateManagerRequest;

import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  12:24 2020/4/25
 */
public interface ManagerService {

    /**
     * 描述: 添加后台管理账号
     *
     * @Author: chengst
     * @Date: 12:22 2020/4/25
     */
    boolean addUser(AddManagerRequest managerRequest);

    /**
     * 描述: 查找所有后台用户
     *
     * @Author: chengst
     * @Date: 21:11 2021-4-10
     */
    List<Manager> findAllUser(ManagerRequest managerRequest);


    /**
     * 根据id查询后台账户信息
     *
     * @return com.pojo.Manager
     * @author cheng
     * @date 2021-7-20 11:34
     */
    Manager selectManagerById(Integer id);


    /**
     * 描述: 修改后台用户
     * * @Author: chengst
     *
     * @Date: 21:12 2021-4-10
     */
    boolean updateUser(UpdateManagerRequest updateManagerRequest);
}
