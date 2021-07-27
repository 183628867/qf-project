package com.pojo.response;

import com.pojo.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auth chengst
 * @Date Created in  12:37 2020/4/25
 */
public class ManagerReponse extends PageResponse {

    private List<Manager> userList;

    private int counts;

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public List<Manager> getUserList() {
        return userList;
    }

    @SuppressWarnings("unchecked")
    public void setUserList(List<Manager> userList) {
        try {
            this.userList = (List<Manager>) userList.get(0);
            List<Map<String, Object>> list = new ArrayList<>();
            list = (List<Map<String, Object>>) userList.get(1);
            this.counts = Integer.parseInt(list.get(0).get("counts").toString());
        } catch (Exception e) {
            this.userList = userList;
        }
    }
}
