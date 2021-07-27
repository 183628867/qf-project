package com.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  14:36 2020/5/1
 */
public class Menu {

    // 菜单id
    private String id;
    // 菜单名称
    private String name;
    // 父菜单名称
    private String parentName;
    // 父菜单id
    private String parentId;
    // 菜单url
    private String path;
    // 菜单图标
    private String icon;
    // 菜单顺序
    private int order;
    // 子菜单
    private List<Menu> children;
    //  添加菜单时间
    private String insertDt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getInsertDt() {
        return insertDt;
    }

    public void setInsertDt(String insertDt) {
        this.insertDt = insertDt;
    }
}
