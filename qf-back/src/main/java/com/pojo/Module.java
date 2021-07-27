package com.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  18:28 2020/6/11
 */
public class Module {

    private Integer id;
    private String name;
    private Integer menuId;
    private String menuUrl;
    private String menuName;
    private String icon;
    private List<Module> menus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Module> getMenus() {
        return menus;
    }

    public void setMenus(List<Module> menus) {
        this.menus = menus;
    }
}
