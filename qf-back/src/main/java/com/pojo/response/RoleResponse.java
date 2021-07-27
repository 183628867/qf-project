package com.pojo.response;

import com.pojo.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auth chengst
 * @Date Created in  17:43 2020/7/6
 */
public class RoleResponse extends PageResponse {

    private Integer counts;

    private List<Role> roleList;


    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    @SuppressWarnings("unchecked")
    public void setRoleList(List<Role> roleList) {
        try {
            this.roleList = (List<Role>) roleList.get(0);
            List<Map<String, Object>> list = new ArrayList<>();
            list = (List<Map<String, Object>>) roleList.get(1);
            this.counts = Integer.parseInt(list.get(0).get("counts").toString());
        } catch (Exception e) {
            this.roleList = roleList;
        }
    }
}
