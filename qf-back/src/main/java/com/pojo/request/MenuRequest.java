package com.pojo.request;

import lombok.Data;

/**
 * @Auth chengst
 * @Date Created in  13:54 2020/5/15
 */
public class MenuRequest extends PageRequest{
    //  角色id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
