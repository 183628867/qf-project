package com.service.impl;

import com.dao.MenuMapper;
import com.pojo.Menu;
import com.pojo.request.AddMenuRequest;
import com.pojo.request.MenuRequest;
import com.pojo.request.UpdateMenuRequest;
import com.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  15:03 2020/5/1
 */
@Service
public class MenuServiceImpl implements MenuService {


    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findMenu(String id) {
        List<Menu> menuList = menuMapper.findMenu(id);
        List<Menu> list = new ArrayList<>();
        for (int i = 0; i < menuList.size(); i++) {
            Menu typeEntity = menuList.get(i);
            // 找出根级菜单，（约定所有根级菜单的parentId属性为0）
            if ("0".equals(typeEntity.getParentId())) {
                list.add(typeEntity);
            }
        }
        // 循环根级菜单，为每个根级菜单下的子菜单list赋值。
        for (Menu entity : list) {
            entity.setChildren(getChildNodes(entity.getId(), menuList));
        }
        return list;
    }


    /**
     * 描述: 查询所有此角色的所有菜单信息，不处理
     *
     * @Author: chengst
     * @Date: 10:37 2021-4-16
     */
    @Override
    public List<Menu> findMenus(String roleId) {
        return menuMapper.findMenu(roleId);
    }

    @Override
    public List<Menu> selectMenus(MenuRequest menuRequest) {
        List<Menu> menus = menuMapper.selectMenus(menuRequest);
        List<Menu> list = new ArrayList<>();
        for (int i = 0; i < menus.size(); i++) {
            Menu typeEntity = menus.get(i);
            // 找出根级菜单，（约定所有根级菜单的parentId属性为0）
            if ("0".equals(typeEntity.getParentId())) {
                list.add(typeEntity);
            }
        }
        // 循环根级菜单，为每个根级菜单下的子菜单list赋值。
        for (Menu entity : list) {
            entity.setChildren(getChildNodes(entity.getId(), menus));
        }
        return list;
    }

    @Override
    public boolean addMenu(AddMenuRequest addMenuRequest) {
        return menuMapper.addMenu(addMenuRequest);
    }

    @Override
    public boolean updateMenu(UpdateMenuRequest updateMenuRequest) {
        return menuMapper.updateMenu(updateMenuRequest);
    }


    @Override
    public Integer selectMenuCount(UpdateMenuRequest updateMenuRequest) {
        return menuMapper.selectMenuCount(updateMenuRequest);
    }


    @Override
    public boolean deleteMenu(UpdateMenuRequest updateMenuRequest) {
        return menuMapper.deleteMenu(updateMenuRequest);
    }


    /**
     * Return the list of children node from percent menu id
     *
     * @param id       current menu id
     * @param rootList all list of menus
     * @return
     */
    private List<Menu> getChildNodes(String id, List<Menu> rootList) {
        // The list of child nodes
        List<Menu> childList = new ArrayList<>();
        // Fill the list of child'nodes which parent id equal params of id
        for (Menu typeEntity : rootList) {
            if (StringUtils.isNotBlank(typeEntity.getParentId())) {
                if (id.equals(typeEntity.getParentId())) {
                    childList.add(typeEntity);
                }
            }
        }
        if (childList.size() == 0) {
            return null;
        }
        // Look up it's child node and fill
        for (Menu entity : childList) {
            entity.setChildren(getChildNodes(entity.getId(), rootList));
        }
        return childList;
    }
}
