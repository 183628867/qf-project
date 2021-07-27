package com.pojo.response;

import com.pojo.Manager;
import com.pojo.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auth chengst
 * @Date Created in  13:55 2020/5/15
 */
public class MenuResponse extends PageResponse {

    private List<Menu> menuList;
    private int counts;

    @SuppressWarnings("unchecked")
    public void setMenuList(List<Menu> menuList) {
        try {
            this.menuList = (List<Menu>) menuList.get(0);
            List<Map<String, Object>> list = new ArrayList<>();
            list = (List<Map<String, Object>>) menuList.get(1);
            this.counts = Integer.parseInt(list.get(0).get("counts").toString());
        } catch (Exception e) {
            this.menuList = menuList;
        }
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }
}
