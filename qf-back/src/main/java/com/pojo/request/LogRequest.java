package com.pojo.request;

/**
 * TODO *
 *
 * @author cheng *
 * @version 1.0 *
 * @date 2021-7-8 15:02
 */

public class LogRequest extends PageRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
