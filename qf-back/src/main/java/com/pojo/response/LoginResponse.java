package com.pojo.response;

import com.pojo.Manager;
import com.pojo.Menu;
import lombok.Data;

import java.util.List;

/**
 * @author chengst
 * @date 2020/3/25
 **/
public class LoginResponse extends PageResponse {

    private String token;

    private String sessionId;

    private Manager manager;

    private List<Menu> menuList;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
