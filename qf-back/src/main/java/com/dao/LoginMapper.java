package com.dao;

import com.pojo.Manager;
import com.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chengst
 * @date 2020/3/25
 **/
@Mapper
public interface LoginMapper {

    /*
     *  根据账号名查找账号的信息
     * @author chengst
     * @Date  2020/3/25 11:13
     * @version 1.0.0
     **/
    public Manager login(String account);



}
