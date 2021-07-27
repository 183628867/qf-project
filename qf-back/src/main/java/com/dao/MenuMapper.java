package com.dao;

import com.pojo.Menu;
import com.pojo.request.AddMenuRequest;
import com.pojo.request.MenuRequest;
import com.pojo.request.UpdateMenuRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  14:58 2020/5/1
 */
@Mapper
public interface MenuMapper {


    /**
     * 描述: 通过角色id获取用户所能访问得资源路径
     *
     * @Author: chengst
     * @Date: 14:59 2020/5/1
     */
    public List<Menu> findMenu(String roleId);


    //  查询所有的菜单
    public List<Menu> selectMenus(MenuRequest menuRequest);

    //  添加菜单
    public boolean addMenu(AddMenuRequest addMenuRequest);


    public boolean updateMenu(UpdateMenuRequest updateMenuRequest);


    Integer selectMenuCount(UpdateMenuRequest updateMenuRequest);

    public boolean deleteMenu(UpdateMenuRequest updateMenuRequest);
}
