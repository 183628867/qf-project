package com.dao;

import com.pojo.Manager;
import com.pojo.request.AddManagerRequest;
import com.pojo.request.ManagerRequest;
import com.pojo.request.UpdateManagerRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  12:24 2020/4/25
 */
@Mapper
public interface ManagerMapper {


    /**
     * 描述: 添加后台管理账号
     *
     * @Author: chengst
     * @Date: 12:22 2020/4/25
     */
    boolean addManager(AddManagerRequest addManagerRequest);


    /**
     * 描述: 查找所有得管理员信息
     *
     * @Author: chengst
     * @Date: 15:13 2020/6/17
     */
    List<Manager> findAllManager(ManagerRequest managerRequest);



    Manager selectManagerById(Integer id);

    /**
     * 描述: 修改用户信息
     *
     * @Author: chengst
     * @Date: 12:53 2020/6/18
     */
    boolean updateManager(UpdateManagerRequest updateManagerRequest);
}
