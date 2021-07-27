package com.service;

import com.pojo.Menu;
import com.pojo.request.AddMenuRequest;
import com.pojo.request.MenuRequest;
import com.pojo.request.UpdateMenuRequest;

import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  15:03 2020/5/1
 */
public interface MenuService {


    /*
    根据角色id查询菜单权限
     */
    public List<Menu> findMenu(String roleId);


    /*
    根据角色id查询菜单权限
     */
    public List<Menu> findMenus(String roleId);

    /**
     * 查询所有菜单
     *
     * @return
     */
    public List<Menu> selectMenus(MenuRequest menuRequest);


    public boolean addMenu(AddMenuRequest addMenuRequest);



    public boolean updateMenu(UpdateMenuRequest updateMenuRequest);


    Integer selectMenuCount(UpdateMenuRequest updateMenuRequest);

    public boolean deleteMenu(UpdateMenuRequest updateMenuRequest);


}
