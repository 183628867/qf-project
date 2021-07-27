package com.pojo.request;


import javax.validation.constraints.NotNull;

/**
 * @Auth chengst
 * @Date Created in  17:25 2020/8/23
 */
public class AddMenuRequest {

    //  父级id
    private Integer parentId;
    @NotNull(message = "菜单名称不能为空")
    private String menuName;
    private String parentName;
    private String menuPath;
    private String menuIcon;
    @NotNull(message = "排序不能为空")
    private String order;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }


    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
